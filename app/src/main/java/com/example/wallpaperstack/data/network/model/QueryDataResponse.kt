package com.example.wallpaperstack.data.network.model

import com.google.gson.annotations.SerializedName

data class QueryDataResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("tag")
    val tag: String
)