package com.example.wallpaperstack.data.network.model

import com.google.gson.annotations.SerializedName

data class UploaderResponse(

    @SerializedName("user_name")
    val userName: String,

    @SerializedName("group")
    val group: String,

    @SerializedName("avatar")
    val avatar: UploaderAvatarResponse,
)
