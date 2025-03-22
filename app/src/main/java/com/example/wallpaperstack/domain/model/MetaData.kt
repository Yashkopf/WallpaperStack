package com.example.wallpaperstack.domain.model

data class MetaData(
    val currentPage: Int,
    val lastPage: Int,
    val perPage: Int,
    val total: Int,
    val seed: String?
)