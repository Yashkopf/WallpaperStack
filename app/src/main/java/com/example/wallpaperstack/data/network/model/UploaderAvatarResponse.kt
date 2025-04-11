package com.example.wallpaperstack.data.network.model

import com.google.gson.annotations.SerializedName

data class UploaderAvatarResponse(

    @SerializedName("200px")
    val large: String,

    @SerializedName("128px")
    val middle: String,

    @SerializedName("32px")
    val small: String,

    @SerializedName("20px")
    val verySmall: String,
)