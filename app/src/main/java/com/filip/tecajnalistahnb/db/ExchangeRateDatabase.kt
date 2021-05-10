package com.filip.tecajnalistahnb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filip.tecajnalistahnb.data.model.ExchangeRateModel
import org.koin.core.component.KoinComponent

@Database(entities = [ExchangeRateModel::class], version = 1)
abstract class ExchangeRateDatabase : RoomDatabase(), KoinComponent {
    abstract fun exchangeRateDao(): ExchangeRateDao
}