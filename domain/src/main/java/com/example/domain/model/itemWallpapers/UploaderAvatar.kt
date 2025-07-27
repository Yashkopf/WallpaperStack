package com.example.domain.model.itemWallpapers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UploaderAvatar(
    val large: String,
    val middle: String,
    val small: String,
    val verySmall: String,
): Parcelable