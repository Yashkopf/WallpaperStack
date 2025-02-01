package com.example.wallpaperstack.data.network.mappers

import com.example.wallpaperstack.data.network.model.WallpaperInfoResponse
import com.example.wallpaperstack.data.network.model.WallpapersResponse
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.domain.model.Wallpapers
import javax.inject.Inject

class WallpapersResponseToEntityMapper @Inject constructor() {

    fun mapWallpapersResponseToEntity(wallpapersResponse: WallpapersResponse): Wallpapers {
        return Wallpapers(
            data = wallpapersResponse.data,
            meta = wallpapersResponse.meta
        )
    }

    fun mapWallpaperInfoResponseToEntity(wallpaperInfoResponse: WallpaperInfoResponse): WallpaperInfo {
        return WallpaperInfo(
            id = wallpaperInfoResponse.id,
            url = wallpaperInfoResponse.url,
            shortUrl = wallpaperInfoResponse.shortUrl,
            views = wallpaperInfoResponse.views,
            favorites = wallpaperInfoResponse.favorites,
            source = wallpaperInfoResponse.source,
            purity = wallpaperInfoResponse.purity,
            category = wallpaperInfoResponse.category,
            dimensionX = wallpaperInfoResponse.dimensionX,
            dimensionY = wallpaperInfoResponse.dimensionY,
            resolution = wallpaperInfoResponse.resolution,
            ratio = wallpaperInfoResponse.ratio,
            fileSize = wallpaperInfoResponse.fileSize,
            fileType = wallpaperInfoResponse.fileType,
            createdAt = wallpaperInfoResponse.createdAt,
            colors = wallpaperInfoResponse.colors,
            path = wallpaperInfoResponse.path,
            thumbs = wallpaperInfoResponse.thumbs,
        )
    }

}