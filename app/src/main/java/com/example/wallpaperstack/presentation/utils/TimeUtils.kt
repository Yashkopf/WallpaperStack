package com.example.wallpaperstack.presentation.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(inputData: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val date = inputFormat.parse(inputData)
    return outputFormat.format(date!!)

}
