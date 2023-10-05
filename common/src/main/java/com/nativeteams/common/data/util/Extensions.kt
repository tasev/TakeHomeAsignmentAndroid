package com.nativeteams.common.data.util

import com.nativeteams.common.data.models.StockListItem
import com.nativeteams.common.data.models.StockListItemEntity
import com.nativeteams.common.data.util.Constants.DECIMAL_FORMAT_PATTERN
import java.text.DecimalFormat


fun Double?.formatDoubleWithTwoDecimals(): String {
    if (this == null) return "0.00"
    val numberFormat = DecimalFormat(DECIMAL_FORMAT_PATTERN)
    return try {
        numberFormat.format(this)
    } catch (e: Exception) {
        "0.00"
    }
}

fun StockListItem.toDbDataEntity() = StockListItemEntity(
    id = id,
    fullExchangeName = fullExchangeName,
    symbol = symbol,
    previousClose = spark?.previousClose,
    lastClose = spark?.close?.get(0)
)