package com.filip.tecajnalistahnb.data.model

import com.filip.tecajnalistahnb.common.EMPTY

class ExchangeRateModel(
    var date: String = EMPTY,
    var id: Long = 0L,
    var country: String = EMPTY,
    var currency: String = EMPTY,
    var unit: Int = 0,
    var buyingRate: String = EMPTY,
    var sellingRate: String = EMPTY,
    var middleRate: String = EMPTY
)