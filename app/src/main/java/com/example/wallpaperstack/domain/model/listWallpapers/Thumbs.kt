package com.example.wallpaperstack.domain.model.listWallpapers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbs(
    val large: String,
    val original: String,
    val small: String
): Parcelable