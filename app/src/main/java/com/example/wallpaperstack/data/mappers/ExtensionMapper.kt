package com.example.wallpaperstack.data.mappers

import com.example.wallpaperstack.data.network.model.ThumbsResponse
import com.example.wallpaperstack.data.network.model.UploaderAvatarResponse
import com.example.wallpaperstack.data.network.model.UploaderResponse
import com.example.wallpaperstack.data.network.model.WallpaperInfoResponse
import com.example.wallpaperstack.domain.model.Thumbs
import com.example.wallpaperstack.domain.model.Uploader
import com.example.wallpaperstack.domain.model.UploaderAvatar
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
        thumbs = thumbs.toThumbs(),
        total = total,
    )
}

internal fun UploaderAvatarResponse.toAvatar(): UploaderAvatar {
    return UploaderAvatar(
        large = large,
        middle = middle,
        small = small,
        verySmall = verySmall
    )
}

internal fun UploaderResponse.toUploader(): Uploader {
    return Uploader(
        userName = userName,
        group = group,
        avatar = avatar.toAvatar()
    )
}

internal fun ThumbsResponse.toThumbs(): Thumbs {
    return Thumbs(
        large = large,
        original = original,
        small = small
    )
}


