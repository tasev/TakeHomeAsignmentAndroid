package com.nativeteams.common.domain.repository

import com.nativeteams.common.data.models.StockListResponse
import kotlinx.coroutines.flow.Flow
interface RemoteSource {
    fun getSummary() : Flow<StockListResponse>
}