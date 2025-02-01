package com.example.wallpaperstack.di.modules

import androidx.lifecycle.ViewModel
import com.example.wallpaperstack.di.Annotation
import com.example.wallpaperstack.presentation.WallpaperViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @Annotation(WallpaperViewModel::class)
    abstract fun bindWallpaperViewModel(viewModel: WallpaperViewModel): ViewModel
}