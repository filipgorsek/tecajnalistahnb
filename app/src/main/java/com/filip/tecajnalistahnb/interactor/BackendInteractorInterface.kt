package com.filip.tecajnalistahnb.interactor

import com.filip.tecajnalistahnb.common.Result
import com.filip.tecajnalistahnb.data.model.ExchangeRateModel

interface BackendInteractorInterface {

    suspend fun getExchangeRate(): Result<MutableList<ExchangeRateModel>>

}