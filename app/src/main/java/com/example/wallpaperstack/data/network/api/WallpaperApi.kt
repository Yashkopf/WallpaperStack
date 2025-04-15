package com.example.wallpaperstack.data.network.api

import androidx.annotation.IntRange
import com.example.wallpaperstack.data.network.model.itemWallpapers.WallpaperItemResponse
import com.example.wallpaperstack.data.network.model.listWallpapers.WallpapersResponse
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
        @Query("order") order: String? = ORDER_DESC,
        @Query("ai_art_filter") aiFilter: String = AI_FILTER,
        @Query("page") @IntRange(from = 1) page: Int = 1,
    ): Response<WallpapersResponse>

    @GET("api/v1/w/{id}")
     suspend fun getWallpaperInfo(
        @Path("id") id: String,
    ): Response<WallpaperItemResponse>

    companion object {

        const val ORDER_DESC = "asc"
        const val CATEGORIES_NUMBER = "100"
        const val PURITY_NUMBER = "100"
        const val RATIO = "portrait"
        const val AI_FILTER = "0"

    }
}