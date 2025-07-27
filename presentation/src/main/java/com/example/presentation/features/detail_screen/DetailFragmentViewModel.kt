package com.example.presentation.features.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.itemWallpapers.WallpaperSingleDetails
import com.example.domain.usecases.getWallpaperInfo.GetWallpaperInfoUseCase
import com.example.presentation.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class DetailFragmentViewModel(
    private val getWallpaperInfoUseCase: GetWallpaperInfoUseCase
): ViewModel() {

    private val wallpaperMutableStateFlow = MutableStateFlow<WallpaperSingleDetails?>(null)
    val wallpapersStateFlow = wallpaperMutableStateFlow.asStateFlow()

    private val _errorStateFlow = MutableSharedFlow<Int>()
    val errorSharedFlow = _errorStateFlow.asSharedFlow()

    fun getWallpaperInfo(id: String?) {
        viewModelScope.launch {
            if (id == null) {
                return@launch _errorStateFlow.emit(R.string.app_name)
            }
            val result = getWallpaperInfoUseCase.invoke(id)
            result.fold(
                onSuccess = {
                    wallpaperMutableStateFlow.value = it
                },
                onFailure = {
                    _errorStateFlow.emit(R.string.app_name)
                }
            )
        }
    }
}