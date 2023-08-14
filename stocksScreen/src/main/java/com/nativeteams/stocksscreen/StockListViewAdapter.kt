package com.nativeteams.stocksscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nativeteams.common.domain.models.StockListItem
import com.nativeteams.stocksscreen.databinding.StockItemBinding
import javax.inject.Inject

class StockListViewAdapter @Inject constructor() :
    ListAdapter<StockListItem, RecyclerView.ViewHolder>(
        stocksDiffCallback
    ) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as StockItemViewHolder).bind(it) }
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
        val stocksDiffCallback = object : DiffUtil.ItemCallback<StockListItem>() {
            override fun areItemsTheSame(oldItem: StockListItem, newItem: StockListItem): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(
                oldItem: StockListItem,
                newItem: StockListItem
            ): Boolean {
                TODO("Not yet implemented")
            }
        }
    }

    inner class StockItemViewHolder(private val binding: StockItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banksListItem: StockListItem) {
        }
    }
}