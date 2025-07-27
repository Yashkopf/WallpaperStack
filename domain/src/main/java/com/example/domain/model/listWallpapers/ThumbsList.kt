package com.example.domain.model.listWallpapers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThumbsList(
    val large: String,
    val original: String,
    val small: String
): Parcelable