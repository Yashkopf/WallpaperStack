package com.example.wallpaperstack.data.network.api

import com.example.wallpaperstack.data.network.model.WallpaperInfoResponse
import com.example.wallpaperstack.data.network.model.WallpapersResponse
import dagger.Module
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Path


interface WallpaperApi {

    @GET("api/v1/search")
    suspend fun getListOfWallpapers(): WallpapersResponse

    @GET("api/v1/w/{id}")
    suspend fun getWallpaper(@Path("id") id: Int): WallpaperInfoResponse

    companion object {
        const val BASE_URL = "https://wallhaven.cc"
    }
}