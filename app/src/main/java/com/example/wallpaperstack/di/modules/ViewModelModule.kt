package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.presentation.features.bottom_sheet_details.BottomSheetFragmentViewModel
import com.example.wallpaperstack.presentation.features.bottom_sheet_settings.BottomSheetSettingsViewModel
import com.example.wallpaperstack.presentation.features.detail_screen.DetailFragmentViewModel
import com.example.wallpaperstack.presentation.features.home_screen.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(
        ::HomeViewModel
    )

    viewModelOf(
        ::BottomSheetFragmentViewModel
    )

    viewModelOf(
        ::DetailFragmentViewModel
    )

    viewModelOf(
        ::BottomSheetSettingsViewModel
    )
}