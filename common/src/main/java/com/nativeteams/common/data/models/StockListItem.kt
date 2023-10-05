package com.nativeteams.common.data.models

import androidx.room.PrimaryKey

data class StockListItem(
    @PrimaryKey (autoGenerate = true) val id: Int,
    val fullExchangeName: String?,
    val symbol: String?,
    val spark: SparkData?
)

data class SparkData(
    val previousClose: Double?,
    val close: List<Double?>?
)