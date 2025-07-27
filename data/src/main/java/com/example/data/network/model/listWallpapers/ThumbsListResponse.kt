package com.example.data.network.model.listWallpapers

import com.google.gson.annotations.SerializedName

data class ThumbsListResponse(

    @SerializedName("large")
    val large: String,

    @SerializedName("original")
    val original: String,

    @SerializedName("small")
    val small: String,
)