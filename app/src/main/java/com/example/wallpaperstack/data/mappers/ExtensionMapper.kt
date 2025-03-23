package com.example.wallpaperstack.data.mappers

import com.example.wallpaperstack.data.network.model.WallpaperInfoResponse
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
        total = total,
    )
}

//internal fun TagsResponse.toTags(): Tags {
//    return Tags(
//        id = id,
//        name = name,
//        alias = alias,
//        categoryId = categoryId,
//        category = category,
//        purity = purity,
//        createdAt = createdAt
//    )
//}


