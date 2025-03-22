package com.example.wallpaperstack.di.components

import android.app.Application
import com.example.wallpaperstack.App
import com.example.wallpaperstack.di.modules.ActivityModule
import com.example.wallpaperstack.di.modules.FragmentModule
import com.example.wallpaperstack.di.modules.NetworkModule
import com.example.wallpaperstack.di.modules.RepositoryModule
import com.example.wallpaperstack.di.modules.UseCaseModule
import com.example.wallpaperstack.di.modules.ViewModelModule
import com.example.wallpaperstack.presentation.HomeFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        RepositoryModule::class,
        ActivityModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        UseCaseModule::class,
        FragmentModule::class]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)

}