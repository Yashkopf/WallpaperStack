package com.example.wallpaperstack.data.network.model.itemWallpapers

import com.google.gson.annotations.SerializedName

data class WallpaperItemResponse (

    @SerializedName("data")
    val data: WallpaperItemInfoResponse,

    )