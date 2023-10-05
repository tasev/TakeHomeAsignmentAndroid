package com.nativeteams.common.data.repository

import com.nativeteams.common.data.db.StockItemListDao
import com.nativeteams.common.data.models.StockListItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val stockItemListDao: StockItemListDao) {
    suspend fun addStockListItem(stock: StockListItemEntity) = stockItemListDao.insert(stock)
    fun getAllStockListItems(): Flow<List<StockListItemEntity>> =
        stockItemListDao.getAllStockItems().flowOn(Dispatchers.IO)
            .conflate()
    suspend fun addAllStockListItem(stocks: List<StockListItemEntity>) =
        stockItemListDao.saveAllStockItems(stocks)

}