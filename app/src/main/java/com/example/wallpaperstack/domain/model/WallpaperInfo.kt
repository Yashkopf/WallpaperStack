package com.example.wallpaperstack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WallpaperInfo (
    val id: String,
    val url: String,
    val shortUrl: String?,
    val views: Int,
    val favorites: Int,
    val source: String,
    val purity: String,
    val category: String,
    val sorting: String?,
    val dimensionX: Int,
    val dimensionY: Int,
    val resolution: String,
    val ratio: String,
    val fileSize: Int,
    val fileType: String?,
    val createdAt: String?,
    val colors: List<String>,
    val path: String,
    val thumbs: Thumbs,
    val tag: Tag?
): Parcelable
