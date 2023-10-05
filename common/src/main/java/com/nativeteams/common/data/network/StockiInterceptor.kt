package com.nativeteams.common.data.network

import com.nativeteams.common.data.util.Constants.API_HOST
import com.nativeteams.common.data.util.Constants.API_KEY
import com.nativeteams.common.data.util.Constants.API_REGION
import okhttp3.Interceptor
import okhttp3.Response

class StockiInterceptor : Interceptor {
    override fun intercept(chain : Interceptor.Chain) : Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("region", API_REGION)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url( newUrl )
            .addHeader("X-RapidAPI-Key", API_KEY)
            .addHeader("X-RapidAPI-Host", API_HOST)
            .build()
        return chain.proceed( newRequest )
    }
}