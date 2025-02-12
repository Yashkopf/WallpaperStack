package com.example.wallpaperstack.presentation

import android.os.Parcelable
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val getWallpaperListUseCase: GetWallpaperListUseCase,
) : ViewModel() {

    private val sorting = MutableStateFlow(Sorting.DATE_ADDED)

    private val _buttonState =
        MutableStateFlow<Pair<Int, Int>>(UNSELECTED_VALUE to Sorting.DATE_ADDED.ordinal)
    val buttonState: StateFlow<Pair<Int, Int>> = _buttonState


    val wallpapers = sorting.flatMapLatest { sortingType ->
        getWallpaperListUseCase.invoke(sortingType)
            .cachedIn(viewModelScope)
    }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    fun sortWallpapers(buttonIndex: Int) {
        val firstStateButton = _buttonState.value.second

        this.sorting.value = Sorting.entries[buttonIndex]
        _buttonState.value = firstStateButton to buttonIndex
    }

    companion object {
        const val UNSELECTED_VALUE = -1
    }
}