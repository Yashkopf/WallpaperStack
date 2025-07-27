package com.example.data.network.model.listWallpapers

import com.example.data.network.model.itemWallpapers.UploaderResponse
import com.google.gson.annotations.SerializedName

data class WallpapersListDetailsResponse (

    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("short_url")
    val shortUrl: String,

    @SerializedName("uploader")
    val uploader: UploaderResponse,

    @SerializedName("views")
    val views: Int,

    @SerializedName("favorites")
    val favorites: Int,

    @SerializedName("source")
    val source: String,

    @SerializedName("purity")
    val purity: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("sorting")
    val sorting: String,

    @SerializedName("dimension_x")
    val dimensionX: Int,

    @SerializedName("dimension_y")
    val dimensionY: Int,

    @SerializedName("resolution")
    val resolution: String,

    @SerializedName("ratio")
    val ratio: String,

    @SerializedName("file_size")
    val fileSize: Int,

    @SerializedName("file_type")
    val fileType: String,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("colors")
    val colors: List<String>,

    @SerializedName("path")
    val path: String,

    @SerializedName("thumbs")
    val thumbs: ThumbsListResponse?,

    @SerializedName("total")
    val total: Int,
)
