package com.filip.tecajnalistahnb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filip.tecajnalistahnb.common.EMPTY
import com.google.gson.annotations.SerializedName

@Entity(tableName = "exchangeRate")
class ExchangeRateModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "Vrijeme Spremanja u bazu")
    var databaseDate:String = EMPTY,

    @ColumnInfo(name = "Datum primjene")
    @SerializedName("Datum primjene")
    var date: String = EMPTY,

    @ColumnInfo(name = "Država")
    @SerializedName("Država")
    var country: String = EMPTY,

    @ColumnInfo(name = "Valuta")
    @SerializedName("Valuta")
    var currency: String = EMPTY,

    @ColumnInfo(name = "Jedinica")
    @SerializedName("Jedinica")
    var unit: Int = 0,

    @ColumnInfo(name = "Kupovni za devize")
    @SerializedName("Kupovni za devize")
    var buyingRate: String = EMPTY,

    @ColumnInfo(name = "Prodajni za devize")
    @SerializedName("Prodajni za devize")
    var sellingRate: String = EMPTY,

    @ColumnInfo(name = "Srednji za devize")
    @SerializedName("Srednji za devize")
    var middleRate: String = EMPTY
)