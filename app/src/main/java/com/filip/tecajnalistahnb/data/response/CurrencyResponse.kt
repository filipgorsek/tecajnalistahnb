package com.filip.tecajnalistahnb.data.response

import com.filip.tecajnalistahnb.data.model.ExchangeRateModel
import java.io.Serializable

data class CurrencyResponse(var exchangeRate: MutableList<ExchangeRateModel> = mutableListOf()) : Serializable
