package com.example.wallpaperstack.data.network.model

import com.google.gson.annotations.SerializedName

data class ThumbsResponse(

    @SerializedName("large")
    val large: String,

    @SerializedName("original")
    val original: String,

    @SerializedName("small")
    val small: String
)