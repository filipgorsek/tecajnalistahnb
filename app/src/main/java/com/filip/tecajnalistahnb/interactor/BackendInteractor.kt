package com.filip.tecajnalistahnb.interactor

import com.filip.tecajnalistahnb.api.ApiService
import com.filip.tecajnalistahnb.common.*
import com.filip.tecajnalistahnb.data.model.ExchangeRateModel
import org.koin.core.component.KoinComponent

const val ERROR_CODE = 404

class BackendInteractor(private val apiService: ApiService) : BackendInteractorInterface,
    KoinComponent {

    override suspend fun getExchangeRate(): Result<MutableList<ExchangeRateModel>> {
        apiService.getCurrency()
            .awaitResult()
            .onSuccess { return Success(it) }
            .onError { return handleError(it) }
        return Failure(HttpError(Throwable(), ERROR_CODE))
    }

    private fun handleError(error: HttpError): Failure {
        return Failure(error)
    }
}