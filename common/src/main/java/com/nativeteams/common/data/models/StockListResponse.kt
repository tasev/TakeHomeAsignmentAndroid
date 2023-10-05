package com.nativeteams.common.data.models

data class StockListResponse(
    val marketSummaryAndSparkResponse: MarketSummaryAndSparkResponseData = MarketSummaryAndSparkResponseData()
)

data class MarketSummaryAndSparkResponseData(
    val result: List<StockListItem> = listOf()
)

