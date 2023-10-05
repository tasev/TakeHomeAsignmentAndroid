package com.nativeteams.common.data.repository

import com.nativeteams.common.data.models.StockListResponse
import com.nativeteams.common.data.source.RemoteSourceImpl
import com.nativeteams.common.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSourceImpl
) : RemoteRepository {
    override fun getSummary(): Flow<StockListResponse> {
        return remoteSource.getSummary()
    }
}