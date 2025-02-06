package com.example.wallpaperstack.data.network.model

import com.example.wallpaperstack.domain.model.Sorting
import com.example.wallpaperstack.domain.model.Tag
import com.example.wallpaperstack.domain.model.Thumbs
import com.google.gson.annotations.SerializedName

data class WallpaperInfoResponse (
    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("shortUrl")
    val shortUrl: String,

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

    @SerializedName("dimensionX")
    val dimensionX: Int,

    @SerializedName("dimensionY")
    val dimensionY: Int,

    @SerializedName("resolution")
    val resolution: String,

    @SerializedName("ratio")
    val ratio: String,

    @SerializedName("fileSize")
    val fileSize: Int,

    @SerializedName("fileType")
    val fileType: String,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("colors")
    val colors: List<String>,

    @SerializedName("path")
    val path: String,

    @SerializedName("thumbs")
    val thumbs: Thumbs,

    @SerializedName("tag")
    val tag: Tag
)
