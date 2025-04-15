package com.example.wallpaperstack.data.mappers

import com.example.wallpaperstack.data.network.model.listWallpapers.ThumbsResponse
import com.example.wallpaperstack.data.network.model.itemWallpapers.UploaderAvatarResponse
import com.example.wallpaperstack.data.network.model.itemWallpapers.UploaderResponse
import com.example.wallpaperstack.data.network.model.listWallpapers.WallpaperInfoResponse
import com.example.wallpaperstack.data.network.model.itemWallpapers.WallpaperItemResponse
import com.example.wallpaperstack.domain.model.listWallpapers.Thumbs
import com.example.wallpaperstack.domain.model.itemWallpapers.Uploader
import com.example.wallpaperstack.domain.model.itemWallpapers.UploaderAvatar
import com.example.wallpaperstack.domain.model.itemWallpapers.WallpaperItemInfo
import com.example.wallpaperstack.domain.model.listWallpapers.WallpaperInfo


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
        thumbs = thumbs?.toThumbs(),
        total = total,
    )
}

internal fun WallpaperItemResponse.toWallTest(): WallpaperItemInfo {
    return WallpaperItemInfo(
        id = this.data.id,
        uploader = this.data.uploader.toUploader(),
        views = this.data.views,
        favorites = this.data.favorites,
        purity = this.data.purity,
        category = this.data.category,
        resolution = this.data.resolution,
        ratio = this.data.ratio,
        fileSize = this.data.fileSize,
        createdAt = this.data.createdAt,
        colors = this.data.colors,
        path = this.data.path,
        thumbs = this.data.thumbs?.toThumbs(),
        total = this.data.total,
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

internal fun ThumbsResponse.toThumbs(): Thumbs? {
    return Thumbs(
        large = large,
        original = original,
        small = small
    )
}



