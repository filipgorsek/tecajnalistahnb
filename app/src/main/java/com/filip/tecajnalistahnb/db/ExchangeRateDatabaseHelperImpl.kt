package com.filip.tecajnalistahnb.db

import com.filip.tecajnalistahnb.data.model.ExchangeRateModel
import org.koin.core.component.KoinComponent

class ExchangeRateDatabaseHelperImpl(private val exchangeRateDatabase: ExchangeRateDatabase) :
    ExchangeRateDatabaseHelper, KoinComponent {

    override suspend fun storeExchangeRate(exchangeRateModel: ExchangeRateModel) {
        exchangeRateDatabase.exchangeRateDao().storeExchangeRateModel(exchangeRateModel)
    }

    override suspend fun getExchangeRate(): List<ExchangeRateModel> =
        exchangeRateDatabase.exchangeRateDao().getAll()

    override suspend fun deleteData() = exchangeRateDatabase.exchangeRateDao().clearDatabase()
}