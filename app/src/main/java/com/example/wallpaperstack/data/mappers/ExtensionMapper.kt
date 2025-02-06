package com.example.wallpaperstack.data.mappers

import com.example.wallpaperstack.data.network.model.WallpaperInfoResponse
import com.example.wallpaperstack.domain.model.WallpaperInfo


internal fun WallpaperInfoResponse.toWallpapersInfo(): WallpaperInfo {
    return WallpaperInfo(
        id = id,
        url = url,
        shortUrl = shortUrl,
        views = views,
        favorites = favorites,
        source = source,
        purity = purity,
        category = category,
        sorting = sorting,
        dimensionX = dimensionX,
        dimensionY = dimensionY,
        resolution = resolution,
        ratio = ratio,
        fileSize = fileSize,
        fileType = fileType,
        createdAt = createdAt,
        colors = colors,
        path = path,
        thumbs = thumbs,
        tag = tag
    )
}

