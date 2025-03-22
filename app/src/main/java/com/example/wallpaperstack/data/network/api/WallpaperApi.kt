package com.example.wallpaperstack.data.network.api

import androidx.annotation.IntRange
import com.example.wallpaperstack.data.network.model.MetaDataResponse
import com.example.wallpaperstack.data.network.model.WallpaperInfoResponse
import com.example.wallpaperstack.data.network.model.WallpapersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WallpaperApi {

    @GET("api/v1/search")
    suspend fun getListOfWallpapers(
        @Query("q") searchQuery: String? = null,
        @Query("categories") categories: String = CATEGORIES_NUMBER,
        @Query("purity") purity: String = PURITY_NUMBER,
        @Query("ratios") ratios: String = RATIO,
        @Query("sorting") sorting: String? = null,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("ai_art_filter") aiFilter: String = AI_FILTER
    ): Response<WallpapersResponse>

    @GET("api/v1/w/{id}")
    suspend fun getWallpaper(
        @Path("id") id: Int,
    ): Response<WallpaperInfoResponse>

    companion object {

        const val CATEGORIES_NUMBER = "101"
        const val PURITY_NUMBER = "110"
        const val RATIO = "landscape%2Cportrait"
        const val AI_FILTER = "0"

    }
}