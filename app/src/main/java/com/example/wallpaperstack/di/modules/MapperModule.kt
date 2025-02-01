package com.example.wallpaperstack.di.modules

import com.example.wallpaperstack.data.network.mappers.WallpapersResponseToEntityMapper
import com.example.wallpaperstack.data.network.model.WallpapersResponse
import com.example.wallpaperstack.domain.model.Wallpapers
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun wallpapersResponseToEntityMapper(): WallpapersResponseToEntityMapper{
        return WallpapersResponseToEntityMapper()
    }
}