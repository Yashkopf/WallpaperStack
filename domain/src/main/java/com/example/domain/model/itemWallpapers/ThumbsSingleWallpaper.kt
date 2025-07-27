package com.example.domain.model.itemWallpapers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThumbsSingleWallpaper(
    val large: String,
    val original: String,
    val small: String
): Parcelable