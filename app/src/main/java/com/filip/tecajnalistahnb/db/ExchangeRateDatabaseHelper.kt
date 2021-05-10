package com.filip.tecajnalistahnb.db

import com.filip.tecajnalistahnb.data.model.ExchangeRateModel

interface ExchangeRateDatabaseHelper {

    suspend fun storeExchangeRate(exchangeRateModel:ExchangeRateModel)

    suspend fun getExchangeRate(): List<ExchangeRateModel>

    suspend fun deleteData()
}