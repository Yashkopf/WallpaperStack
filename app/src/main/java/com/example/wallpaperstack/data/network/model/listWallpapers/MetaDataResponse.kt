package com.example.wallpaperstack.data.network.model.listWallpapers

import com.google.gson.annotations.SerializedName

data class MetaDataResponse(

    @SerializedName("current_page")
    val currentPage: Int,

    @SerializedName("last_page")
    val lastPage: Int,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("seed")
    val seed: String?,
)