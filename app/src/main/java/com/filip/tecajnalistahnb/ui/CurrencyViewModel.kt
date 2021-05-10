package com.filip.tecajnalistahnb.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.filip.tecajnalistahnb.common.onError
import com.filip.tecajnalistahnb.common.onSuccess
import com.filip.tecajnalistahnb.coroutines.CoroutineContextProviderImpl
import com.filip.tecajnalistahnb.data.model.ExchangeRateModel
import com.filip.tecajnalistahnb.db.ExchangeRateDatabaseHelperImpl
import com.filip.tecajnalistahnb.interactor.BackendInteractorInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext


class CurrencyViewModel(
    private val backend: BackendInteractorInterface,
    private val coroutine: CoroutineContextProviderImpl,
    private val exchangeRateDb: ExchangeRateDatabaseHelperImpl
) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = coroutine.main + job

    private val job = Job()

    val currencyExchangeLiveData = MutableLiveData<MutableList<ExchangeRateModel>>()
    var databaseExchangeRate = arrayListOf<ExchangeRateModel>()

    val calendar = Calendar.getInstance()

    @SuppressLint("SimpleDateFormat")
    fun checkDbData() {
        launch(coroutine.io) {
            databaseExchangeRate = exchangeRateDb.getExchangeRate() as ArrayList<ExchangeRateModel>
            if (databaseExchangeRate.isNullOrEmpty()) {
                getCurrencyStatus()
            } else {
                if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendar.get(
                        Calendar.DAY_OF_WEEK
                    ) == Calendar.SUNDAY)
                ) {
                    currencyExchangeLiveData.postValue(databaseExchangeRate)
                } else {
                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                    val databaseDate = simpleDateFormat.parse(databaseExchangeRate[0].databaseDate)
                    val currentTime = Date()
                    val cal1 = Calendar.getInstance()
                    val cal2 = Calendar.getInstance()
                    cal1.time = databaseDate
                    cal2.time = currentTime
                    val sameDay = cal1[Calendar.DAY_OF_YEAR] === cal2[Calendar.DAY_OF_YEAR] &&
                                  cal1[Calendar.YEAR] === cal2[Calendar.YEAR]
                    if (!sameDay) {
                        getCurrencyStatus()
                    } else {
                        currencyExchangeLiveData.postValue(databaseExchangeRate)
                    }
                }
                currencyExchangeLiveData.postValue(databaseExchangeRate)
            }
        }
    }

    private fun getCurrencyStatus() {
        launch(coroutine.io) {
            backend.getExchangeRate()
                .onSuccess {
                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                    exchangeRateDb.deleteData()
                    currencyExchangeLiveData.postValue(it)
                    it.forEach {
                        val strDate: String = simpleDateFormat.format(Date())
                        it.databaseDate = strDate
                        exchangeRateDb.storeExchangeRate(it)
                    }
                }
                .onError {
                    currencyExchangeLiveData.postValue(databaseExchangeRate)
                }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}