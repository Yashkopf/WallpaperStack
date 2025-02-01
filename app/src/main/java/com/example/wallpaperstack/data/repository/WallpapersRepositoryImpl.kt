package com.example.wallpaperstack.data.repository

import com.example.wallpaperstack.data.network.api.WallpaperApi
import com.example.wallpaperstack.data.network.mappers.WallpapersResponseToEntityMapper
import com.example.wallpaperstack.data.network.utils.safeApiCall
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.domain.model.Wallpapers
import javax.inject.Inject

class WallpapersRepositoryImpl @Inject constructor(
    private val api: WallpaperApi,
    private val mapper: WallpapersResponseToEntityMapper
): WallpapersRepository {


    override suspend fun getWallpapersList(): Result<Wallpapers> {
        return safeApiCall {
            Result.success(api.getListOfWallpapers()).map { value ->
                mapper.mapWallpapersResponseToEntity(value)
            }
        }
    }

    override suspend fun getWallpaperInfo(wallpaperId: Int): Result<WallpaperInfo> {
        return safeApiCall {
            Result.success(api.getWallpaper(wallpaperId)).map{ value ->
                mapper.mapWallpaperInfoResponseToEntity(value)
            }
        }
    }
}