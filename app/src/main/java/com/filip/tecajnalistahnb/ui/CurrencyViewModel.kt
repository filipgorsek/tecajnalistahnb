package com.filip.tecajnalistahnb.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.filip.tecajnalistahnb.common.onError
import com.filip.tecajnalistahnb.common.onSuccess
import com.filip.tecajnalistahnb.coroutines.CoroutineContextProviderImpl
import com.filip.tecajnalistahnb.data.response.CurrencyResponse
import com.filip.tecajnalistahnb.interactor.BackendInteractorInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CurrencyViewModel(
    private val backend: BackendInteractorInterface,
    private val coroutine: CoroutineContextProviderImpl) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = coroutine.main + job

    private val job = Job()

    val currencyResponse = MutableLiveData<CurrencyResponse>()

    fun getCurrencyStatus() {
        launch(coroutine.io) {
            backend.getExchangeRate()
                .onSuccess {
                    Log.d("CurrencyResponse", it.toString())
                }
                .onError {
                    Log.d("CurrencyResponse", it.toString())
                }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}