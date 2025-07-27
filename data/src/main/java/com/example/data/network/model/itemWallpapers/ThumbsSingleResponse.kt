package com.example.data.network.model.itemWallpapers

import com.google.gson.annotations.SerializedName

data class ThumbsSingleResponse(

    @SerializedName("large")
    val large: String,

    @SerializedName("original")
    val original: String,

    @SerializedName("small")
    val small: String,
)