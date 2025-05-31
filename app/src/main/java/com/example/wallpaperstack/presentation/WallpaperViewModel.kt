package com.example.wallpaperstack.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpaperstack.data.network.ConnectivityManager
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.usecases.getItemsCount.GetItemsCountUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn


class WallpaperViewModel(
    private val getWallpaperListUseCase: GetWallpaperListUseCase,
    private val connectivityManager: ConnectivityManager,
    private val getItemsCountUseCase: GetItemsCountUseCase,
) : ViewModel() {

    private val sorting = MutableStateFlow(Sorting.DATE_ADDED)
    private val currentQuery = MutableStateFlow<String?>(null)

    private val _buttonState =
        MutableStateFlow<Pair<Int, Int>>(UNSELECTED_VALUE to Sorting.DATE_ADDED.ordinal)
    val buttonState: StateFlow<Pair<Int, Int>> = _buttonState

    val connectivity = connectivityManager.connectionAsStateFlow
    val wallpapersList = combine(sorting, currentQuery) { sorting, query ->
        Pair(sorting, query)
    }.flatMapLatest { (sorting, query) ->
        getWallpaperListUseCase(sorting, query)
    }.cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    val itemsCount =
        getItemsCountUseCase()
            .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun searchWallpapers(query: String) {
        currentQuery.value = query
    }

    fun sortWallpapers(buttonIndex: Int) {
        val initialStateButton = _buttonState.value.second
        this.sorting.value = Sorting.entries[buttonIndex]
        _buttonState.value = initialStateButton to buttonIndex
    }

    companion object {
        const val UNSELECTED_VALUE = -1
    }
}