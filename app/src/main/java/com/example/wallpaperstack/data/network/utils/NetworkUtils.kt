package com.example.wallpaperstack.data.network.utils

suspend fun <T> safeApiCall(call: suspend () -> Result<T>): Result<T> {
    return try {
        call()
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}