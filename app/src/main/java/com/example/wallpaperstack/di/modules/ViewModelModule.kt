package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.presentation.WallpaperViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        WallpaperViewModel(get(), get(), get())
    }
}