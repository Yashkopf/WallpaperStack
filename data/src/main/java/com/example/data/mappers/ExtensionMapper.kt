package com.example.data.mappers

import com.example.data.network.model.itemWallpapers.ThumbsSingleResponse
import com.example.data.network.model.itemWallpapers.UploaderAvatarResponse
import com.example.data.network.model.itemWallpapers.UploaderResponse
import com.example.data.network.model.itemWallpapers.WallpaperSingleResponse
import com.example.data.network.model.listWallpapers.ThumbsListResponse
import com.example.data.network.model.listWallpapers.WallpapersListDetailsResponse
import com.example.domain.model.itemWallpapers.ThumbsSingleWallpaper
import com.example.domain.model.itemWallpapers.Uploader
import com.example.domain.model.itemWallpapers.UploaderAvatar
import com.example.domain.model.itemWallpapers.WallpaperSingleDetails
import com.example.domain.model.listWallpapers.ThumbsList
import com.example.domain.model.listWallpapers.WallpapersListDetails


internal fun WallpapersListDetailsResponse.toWallpapersInfo(): WallpapersListDetails {
    return WallpapersListDetails(
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
        thumbs = thumbs?.toThumbsList(),
        total = total,
    )
}

internal fun WallpaperSingleResponse.toWallpapers(): WallpaperSingleDetails {
    return WallpaperSingleDetails(
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
        thumbs = this.data.thumbs?.toThumbsSingle(),
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

internal fun ThumbsSingleResponse.toThumbsSingle(): ThumbsSingleWallpaper? {
    return ThumbsSingleWallpaper(
        large = large,
        original = original,
        small = small
    )
}

internal fun ThumbsListResponse.toThumbsList(): ThumbsList? {
    return ThumbsList(
        large = large,
        original = original,
        small = small
    )
}



