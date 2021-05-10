package com.filip.tecajnalistahnb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.filip.tecajnalistahnb.data.model.ExchangeRateModel

@Dao
interface ExchangeRateDao {

    @Insert()
    suspend fun storeExchangeRateModel(exchangeRateModel: ExchangeRateModel)

    @Query("SELECT * FROM exchangeRate")
    suspend fun getAll(): List<ExchangeRateModel>

    @Query("DELETE FROM exchangeRate")
    suspend fun clearDatabase()
}