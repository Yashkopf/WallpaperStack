package com.example.wallpaperstack.data.network.model

import com.example.wallpaperstack.domain.model.QueryData
import com.google.gson.annotations.SerializedName

data class MetaDataResponse(

    @SerializedName("currentPage")
    val currentPage: Int,

    @SerializedName("lastPage")
    val lastPage: Int,

    @SerializedName("perPage")
    val perPage: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("query")
    val query: QueryData?,

    @SerializedName("seed")
    val seed: String?
)