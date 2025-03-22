package com.example.wallpaperstack.data.mappers

import com.example.wallpaperstack.data.network.model.MetaDataResponse
import com.example.wallpaperstack.data.network.model.WallpaperInfoResponse
import com.example.wallpaperstack.domain.model.MetaData
import com.example.wallpaperstack.domain.model.WallpaperInfo


internal fun WallpaperInfoResponse.toWallpapersInfo(): WallpaperInfo {
    return WallpaperInfo(
        id = id,
        views = views,
        favorites = favorites,
        purity = purity,
        category = category,
        resolution = resolution,
        ratio = ratio,
        fileSize = fileSize,
        createdAt = createdAt,
        colors = colors,
        path = path,
        thumbs = thumbs,
        tag = tag
    )
}

internal fun MetaDataResponse.toMetaData(): MetaData {
    return MetaData(
        currentPage = currentPage,
        lastPage = lastPage,
        perPage = perPage,
        total = total,
        seed = seed
    )
}

