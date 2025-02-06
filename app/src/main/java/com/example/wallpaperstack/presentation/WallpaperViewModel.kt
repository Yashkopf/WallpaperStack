package com.example.wallpaperstack.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import javax.inject.Inject

class WallpaperViewModel @Inject constructor(
    private val getWallpaperInfoUseCase: GetWallpaperInfoUseCase,
    private val getWallpaperListUseCase: GetWallpaperListUseCase,
) : ViewModel() {

    private val sorting = MutableLiveData(Sorting.DATE_ADDED)

    val wallpapers = sorting.switchMap { sortingType ->
        getWallpaperListUseCase.invoke(sortingType)
    }

    fun searchWallpapers(sorting: Sorting) {
        this.sorting.value = sorting
    }



//    fun getWallpaperInfo(wallpaperId: Int) = getWallpaperInfoUseCase(wallpaperId)
}