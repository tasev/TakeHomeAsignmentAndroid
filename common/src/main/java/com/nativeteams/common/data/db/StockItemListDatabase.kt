package com.nativeteams.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nativeteams.common.data.models.StockListItemEntity

@Database(entities = [StockListItemEntity::class], version = 1, exportSchema = false)
abstract class StockItemListDatabase: RoomDatabase() {
    abstract fun stockItemListDao(): StockItemListDao
}