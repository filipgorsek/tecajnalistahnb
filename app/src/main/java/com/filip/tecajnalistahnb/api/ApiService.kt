package com.filip.tecajnalistahnb.api

import com.filip.tecajnalistahnb.data.response.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("")
    fun getCurrency() : Call<CurrencyResponse>
}