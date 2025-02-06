package com.example.wallpaperstack.di.components

import android.app.Application
import com.example.wallpaperstack.App
import com.example.wallpaperstack.di.modules.ActivityModule
import com.example.wallpaperstack.di.modules.NetworkModule
import com.example.wallpaperstack.di.modules.RepositoryModule
import com.example.wallpaperstack.di.modules.UseCaseModule
import com.example.wallpaperstack.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        RepositoryModule::class,
        ActivityModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        UseCaseModule::class]
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