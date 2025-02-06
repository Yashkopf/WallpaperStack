package com.example.wallpaperstack.domain.model

data class Tag(
    val id: Int,
    val name: String,
    val alias: String?,
    val categoryId: Int,
    val category: String,
    val purity: String,
    val createdAt: String
)