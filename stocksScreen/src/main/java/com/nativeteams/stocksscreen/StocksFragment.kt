package com.nativeteams.stocksscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nativeteams.common.data.util.Resource
import com.nativeteams.stocksscreen.databinding.FragmentStocksBinding
import com.nativeteams.stocksscreen.view_models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StocksFragment : Fragment() {
    private lateinit var binding: FragmentStocksBinding

    private val mainViewModel by viewModels<MainViewModel>()

    private val adapter = StockListViewAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStocksBinding.inflate(inflater, container, false)
        binding.stocksRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        binding.swipeToRefreshStocksView.setOnRefreshListener {
            mainViewModel.getSummary()
        }
    }

    private fun initObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.stockList.collect {
                    when (it) {
                        is Resource.Success -> {
                            binding.swipeToRefreshStocksView.isRefreshing = false
                            adapter.updateStockList(it.result)
                        }

                        is Resource.Failure -> {
                            binding.swipeToRefreshStocksView.isRefreshing = false
                            Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                        }

                        else -> {
                            Log.d("StocksFragment", "loading")
                        }
                    }
                }
            }
        }
    }
}