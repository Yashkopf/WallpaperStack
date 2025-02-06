package com.example.wallpaperstack.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor(private val authToken: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer Rd1ng0LQNIBW36c4hwcyd5ikrZwoRn1H")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}