package com.nativeteams.common.di

import android.content.Context
import androidx.room.Room
import com.nativeteams.common.data.db.StockItemListDao
import com.nativeteams.common.data.db.StockItemListDatabase
import com.nativeteams.common.data.network.NetworkApiService
import com.nativeteams.common.data.network.StockiInterceptor
import com.nativeteams.common.data.repository.LocalRepositoryImpl
import com.nativeteams.common.data.source.RemoteSourceImpl
import com.nativeteams.common.data.util.Constants.BASE_URL
import com.nativeteams.common.data.util.Constants.STOCK_ITEM_LIST_TABLE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(StockiInterceptor()).build()

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

    @Provides
    fun provideApiInstance(retrofit: Retrofit): NetworkApiService =
        retrofit.create(NetworkApiService::class.java)

    @Provides
    fun provideRemoteSourceInstance(api: NetworkApiService): RemoteSourceImpl =
        RemoteSourceImpl(api)

    @Provides
    fun provideLocalSourceInstance(stockItemListDao: StockItemListDao): LocalRepositoryImpl =
        LocalRepositoryImpl(stockItemListDao)


    @Provides
    fun provideStockItemListDao(stockItemListDatabase: StockItemListDatabase): StockItemListDao
            = stockItemListDatabase.stockItemListDao()

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): StockItemListDatabase
            = Room.databaseBuilder(
        context,
        StockItemListDatabase::class.java,
        STOCK_ITEM_LIST_TABLE_NAME)
        .fallbackToDestructiveMigration()
        .build()

}