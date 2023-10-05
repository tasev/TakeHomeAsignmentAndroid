package com.nativeteams.stocksscreen.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nativeteams.common.data.models.StockListItemEntity
import com.nativeteams.common.data.repository.LocalRepositoryImpl
import com.nativeteams.common.data.util.Resource
import com.nativeteams.common.data.util.toDbDataEntity
import com.nativeteams.common.domain.useCase.GetSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSummaryUseCase: GetSummaryUseCase,
    private val localRepositoryImpl: LocalRepositoryImpl
) : ViewModel() {

    private val _stockList =
        MutableStateFlow<Resource<List<StockListItemEntity>>>(Resource.Loading(true))
    val stockList = _stockList.asStateFlow()

    init {
        getAllStockListItemsFromDatabase()
    }

    private fun getAllStockListItemsFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepositoryImpl.getAllStockListItems().distinctUntilChanged()
                .collect { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        _stockList.value = Resource.Failure("Empty Database")
                        getSummary()
                    } else {
                        _stockList.value = Resource.Success(listOfNotes)
                    }

                }
        }
    }

    fun getSummary() {
        viewModelScope.launch {
            getSummaryUseCase()
                .flowOn(Dispatchers.IO)
                .catch {
                    _stockList.value = Resource.Failure("Please check your internet connection")
                }
                .collect {
                    it.marketSummaryAndSparkResponse?.result?.map { it.toDbDataEntity() }
                        ?.let { it1 ->
                            localRepositoryImpl.addAllStockListItem(it1)
                            if (it1.isNotEmpty()) {
                                getAllStockListItemsFromDatabase()
                            }
                        }
                }
        }
    }

}