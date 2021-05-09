package com.filip.tecajnalistahnb.interactor

import com.filip.tecajnalistahnb.common.Result
import com.filip.tecajnalistahnb.data.response.CurrencyResponse

interface BackendInteractorInterface {

    suspend fun getExchangeRate(): Result<CurrencyResponse>

}