package com.example.data.network.model.itemWallpapers

import com.google.gson.annotations.SerializedName

data class UploaderResponse(

    @SerializedName("username")
    val userName: String,

    @SerializedName("group")
    val group: String,

    @SerializedName("avatar")
    val avatar: UploaderAvatarResponse,
)
