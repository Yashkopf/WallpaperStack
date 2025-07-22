package com.example.wallpaperstack.presentation.features.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpaperstack.data.network.ConnectivityManager
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.preferences.PreferencesRepository
import com.example.wallpaperstack.domain.usecases.getItemsCount.GetItemsCountUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn


class HomeViewModel(
    private val getWallpaperListUseCase: GetWallpaperListUseCase,
    private val connectivityManager: ConnectivityManager,
    private val getItemsCountUseCase: GetItemsCountUseCase,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val purity = MutableStateFlow(preferencesRepository.getPurity())
    private val sorting = MutableStateFlow(Sorting.DATE_ADDED)
    private val currentQuery = MutableStateFlow<String?>(null)
    private val categories = MutableStateFlow<String>(initialCategoryState())

    private val _buttonState =
        MutableStateFlow<Pair<Int, Int>>(UNSELECTED_VALUE to Sorting.DATE_ADDED.ordinal)
    val buttonState: StateFlow<Pair<Int, Int>> = _buttonState
    val connectivity = connectivityManager.connectionAsStateFlow

    val wallpapersList = combine(
        sorting,
        currentQuery,
        purity,
        categories
    ) { sorting, query, purity, categories ->
        getWallpaperListUseCase(sorting = sorting, query =  query, purity = purity, categories = categories)
    }.flatMapLatest { wallpapersList ->
        wallpapersList
    }.cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    val itemsCount =
        getItemsCountUseCase()
            .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun searchWallpapers(query: String) {
        currentQuery.value = query
    }

    fun updatePurity(purity: String) {
        this.purity.value = purity
    }

    fun updateCategory(categoryNumber: String){
        categories.value = categoryNumber
    }

    fun initialCategoryState(): String {
        return buildString {
            append( preferencesRepository.getGeneralCategory())
            append( preferencesRepository.getAnimeCategory())
            append( preferencesRepository.getPeopleCategory())
        }
    }

    fun sortWallpapers(buttonIndex: Int) {
        val initialStateButton = _buttonState.value.second
        this.sorting.value = Sorting.entries[buttonIndex]
        if (initialStateButton == buttonIndex) return
        _buttonState.value = initialStateButton to buttonIndex
    }

    companion object {
        const val UNSELECTED_VALUE = -1
    }
}