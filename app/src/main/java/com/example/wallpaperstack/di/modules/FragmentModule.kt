package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.presentation.HomeFragment
import com.example.wallpaperstack.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment
}