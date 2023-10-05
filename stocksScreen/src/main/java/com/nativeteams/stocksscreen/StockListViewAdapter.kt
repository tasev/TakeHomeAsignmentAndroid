package com.nativeteams.stocksscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nativeteams.common.data.models.StockListItemEntity
import com.nativeteams.common.data.util.Helper
import com.nativeteams.stocksscreen.databinding.StockItemBinding
import javax.inject.Inject

class StockListViewAdapter @Inject constructor() :
    ListAdapter<StockListItemEntity, RecyclerView.ViewHolder>(
        stocksDiffCallback
    ) {

    var stockList: List<StockListItemEntity?>? = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateStockList(_stockList: List<StockListItemEntity?>){
        stockList = _stockList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = stockList?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as StockItemViewHolder).bind(it) }
    }

    override fun getItem(position: Int): StockListItemEntity? {
        return stockList?.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        StockItemViewHolder(
            StockItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {
        val stocksDiffCallback = object : DiffUtil.ItemCallback<StockListItemEntity>() {
            override fun areItemsTheSame(oldItem: StockListItemEntity, newItem: StockListItemEntity): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: StockListItemEntity,
                newItem: StockListItemEntity
            ):Boolean = oldItem == newItem
        }
    }

    inner class StockItemViewHolder(private val binding: StockItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stockListItem: StockListItemEntity) {
            binding.stockName.text = stockListItem.symbol
            binding.stockFullName.text = stockListItem.fullExchangeName
            binding.stockValue.text = Helper().getSpannablePriceFiled(stockListItem)
        }
    }

}