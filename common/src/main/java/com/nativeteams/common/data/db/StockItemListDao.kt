package com.nativeteams.common.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nativeteams.common.data.models.StockListItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StockItemListDao {
    @Query(value = "SELECT * from stock_item_list_tbl")
    fun getAllStockItems(): Flow<List<StockListItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllStockItems(stocks: List<StockListItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stock: StockListItemEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(stock: StockListItemEntity)

    @Query("DELETE from stock_item_list_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteStockItemEntity(note: StockListItemEntity)

}
