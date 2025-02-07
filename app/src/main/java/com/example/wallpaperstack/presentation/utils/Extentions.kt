package com.example.wallpaperstack.presentation.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Locale

fun Context.getCustomColor(color:Int): Int {
    return ContextCompat.getColor(this, color)
}

fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)

}