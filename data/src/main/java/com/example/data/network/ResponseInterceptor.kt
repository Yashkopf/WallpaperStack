package com.example.data.network

import okhttp3.Interceptor
import okhttp3.Response

internal class ResponseInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val originalUrl = request.url
        val url = originalUrl.newBuilder().apply {
            addQueryParameter(APIKEY, apiKey)
        }.build()
        val builder = request.newBuilder().url(url)

        return chain.proceed(builder.build())
    }

    private companion object {
        const val APIKEY = "apikey"
    }
}