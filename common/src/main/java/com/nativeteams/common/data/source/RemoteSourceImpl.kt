package com.nativeteams.common.data.source

import com.nativeteams.common.data.network.NetworkApiService
import com.nativeteams.common.domain.repository.RemoteSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val api: NetworkApiService
) : RemoteSource  {
        override fun getSummary() = flow {
            emit( api.getSummary())
        }

}