package com.nativeteams.common.data.network

import com.nativeteams.common.data.models.StockListResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkApiService {

    @GET("market/v2/get-summary")
    suspend fun getSummary() : StockListResponse
}