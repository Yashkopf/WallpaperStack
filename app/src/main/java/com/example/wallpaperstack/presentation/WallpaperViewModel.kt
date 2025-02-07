package com.example.wallpaperstack.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCase
import com.example.wallpaperstack.domain.usecases.getWallpaperList.GetWallpaperListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
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