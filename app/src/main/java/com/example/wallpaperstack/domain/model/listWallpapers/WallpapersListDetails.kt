package com.example.wallpaperstack.domain.model.listWallpapers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WallpapersListDetails (
    val id: String,
    val views: Int,
    val favorites: Int,
    val purity: String,
    val category: String,
    val resolution: String,
    val ratio: String,
    val fileSize: Int,
    val createdAt: String?,
    val colors: List<String>,
    val path: String,
    val thumbs: Thumbs?,
    val total: Int,
): Parcelable
