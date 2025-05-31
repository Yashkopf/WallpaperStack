package com.example.wallpaperstack.domain.model.itemWallpapers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Uploader(
    val userName: String,
    val group: String,
    val avatar: UploaderAvatar,
): Parcelable