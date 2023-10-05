package com.nativeteams.common.data.util

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import com.nativeteams.common.data.models.StockListItemEntity

class Helper {

    fun getSpannablePriceFiled(stockListItem: StockListItemEntity): Spannable {
        val mostRecentClosingPrice = stockListItem.lastClose ?: 0.0
        val previousClosingPrice = stockListItem?.previousClose ?: 0.0

        val diference = mostRecentClosingPrice - previousClosingPrice
        val percentage = 100.0 * diference / previousClosingPrice

        val sign = if (diference <= 0.0) "" else "+"

        val mostRecentClosingPriceFormatted = mostRecentClosingPrice.formatDoubleWithTwoDecimals()
        val differenceFormatted = "$sign${diference.formatDoubleWithTwoDecimals()}"
        val percentageFormatted = "($sign${percentage.formatDoubleWithTwoDecimals()}%)"

        val highlightGreenTexts: ArrayList<String> = arrayListOf()
        val highlightRedTexts: ArrayList<String> = arrayListOf()

        if (sign.isBlank()) {
            highlightRedTexts.add(differenceFormatted)
            highlightRedTexts.add(percentageFormatted)
        } else {
            highlightGreenTexts.add(differenceFormatted)
            highlightGreenTexts.add(percentageFormatted)
        }

        return compileColoredSpanText(
            "$mostRecentClosingPriceFormatted $differenceFormatted $percentageFormatted",
            highlightGreenTexts = highlightGreenTexts,
            highlightRedTexts = highlightRedTexts
        )

    }

    fun compileColoredSpanText(
        textToBeSpanned: String = "",
        highlightGreenTexts: ArrayList<String> = arrayListOf(),
        highlightRedTexts: ArrayList<String> = arrayListOf()
    ): SpannableStringBuilder {
        val finalSpanText = SpannableStringBuilder(textToBeSpanned)
        for (spannedText in highlightGreenTexts) {
            if (spannedText.isBlank()) {
                continue
            }
            val startIndex =
                textToBeSpanned.lowercase()
                    .indexOf(spannedText.lowercase()) // get the startindex of the text to be spanned
            if (startIndex < 0) {
                continue
            }
            val amountOfCharacters = spannedText.length
            try {
                finalSpanText.setSpan(
                    ForegroundColorSpan(Color.GREEN),
                    startIndex,
                    startIndex + amountOfCharacters,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                finalSpanText.setSpan(
                    RelativeSizeSpan(0.8f),
                    startIndex,
                    startIndex + amountOfCharacters,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        for (spannedText in highlightRedTexts) {
            if (spannedText.isBlank()) {
                continue
            }
            val startIndex =
                textToBeSpanned.lowercase()
                    .indexOf(spannedText.lowercase()) // get the startindex of the text to be spanned
            if (startIndex < 0) {
                continue
            }
            val amountOfCharacters = spannedText.length
            try {
                finalSpanText.setSpan(
                    ForegroundColorSpan(Color.RED),
                    startIndex,
                    startIndex + amountOfCharacters,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                finalSpanText.setSpan(
                    RelativeSizeSpan(0.8f),
                    startIndex,
                    startIndex + amountOfCharacters,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return finalSpanText
    }
}