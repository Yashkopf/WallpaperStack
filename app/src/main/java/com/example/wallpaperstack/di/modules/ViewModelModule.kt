package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.presentation.BottomSheetFragmentViewModel
import com.example.wallpaperstack.presentation.WallpaperViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(
        ::WallpaperViewModel
    )

    viewModelOf(
        ::BottomSheetFragmentViewModel
    )
}