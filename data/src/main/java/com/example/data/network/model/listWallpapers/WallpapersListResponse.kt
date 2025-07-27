package com.example.data.network.model.listWallpapers

import com.google.gson.annotations.SerializedName

data class WallpapersListResponse (

    @SerializedName("data")
    val data: List<WallpapersListDetailsResponse>,

    @SerializedName("meta")
    val meta: MetaDataResponse
)
