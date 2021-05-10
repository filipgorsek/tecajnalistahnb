package com.filip.tecajnalistahnb.api

import com.filip.tecajnalistahnb.data.model.ExchangeRateModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("tecajn/v1/")
    fun getCurrency() : Call<MutableList<ExchangeRateModel>>
}