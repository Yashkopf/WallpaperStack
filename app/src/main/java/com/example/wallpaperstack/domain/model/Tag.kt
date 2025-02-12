package com.example.wallpaperstack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    val id: Int,
    val name: String,
    val alias: String?,
    val categoryId: Int,
    val category: String,
    val purity: String,
    val createdAt: String
): Parcelable