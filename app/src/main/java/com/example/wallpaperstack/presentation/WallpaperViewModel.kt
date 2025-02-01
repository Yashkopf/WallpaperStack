package com.example.wallpaperstack.presentation

import androidx.lifecycle.ViewModel
import com.example.wallpaperstack.data.repository.WallpapersRepository
import javax.inject.Inject

class WallpaperViewModel @Inject constructor(
    private val repository: WallpapersRepository
): ViewModel() {

}