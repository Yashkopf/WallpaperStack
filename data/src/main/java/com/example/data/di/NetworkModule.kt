package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.network.ConnectivityManagerImpl
import com.example.data.network.ResponseInterceptor
import com.example.data.network.api.WallpaperApi
import com.example.domain.Connectivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        ResponseInterceptor(BuildConfig.API_KEY)
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .readTimeout(45L, TimeUnit.SECONDS)
            .connectTimeout(45L, TimeUnit.SECONDS)
            .writeTimeout(45L, TimeUnit.SECONDS)
            .addInterceptor(get<ResponseInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(WallpaperApi::class.java)
    }

    single<Connectivity> {
        ConnectivityManagerImpl(androidApplication())
    }
}