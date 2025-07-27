package com.example.data.network.model.itemWallpapers

import com.google.gson.annotations.SerializedName

data class WallpaperSingleResponse (

    @SerializedName("data")
    val data: WallpaperSingleDetailsResponse,

    )