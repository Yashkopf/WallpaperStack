package com.example.wallpaperstack.data.network.model

import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.google.gson.annotations.SerializedName

data class WallpapersResponse (

    @SerializedName("data")
    val data: List<WallpaperInfoResponse>,

    @SerializedName("meta")
    val meta: MetaDataResponse
)
