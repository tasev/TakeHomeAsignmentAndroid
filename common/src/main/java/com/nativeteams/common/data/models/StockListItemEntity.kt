package com.nativeteams.common.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nativeteams.common.data.util.Constants.STOCK_ITEM_LIST_TABLE_NAME

@Entity(tableName = STOCK_ITEM_LIST_TABLE_NAME)
data class StockListItemEntity(
    @PrimaryKey (autoGenerate = true) val id: Int,
    @ColumnInfo(name = "fullExchangeName") val fullExchangeName: String?,
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "previousClose") val previousClose: Double?,
    @ColumnInfo(name = "lastClose") val lastClose: Double?
)