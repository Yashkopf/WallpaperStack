package com.example.wallpaperstack.domain.model

import com.example.wallpaperstack.data.network.model.MetaDataResponse

data class Wallpapers(

    val data: List<WallpaperInfo>,
    val meta: MetaData?,
)