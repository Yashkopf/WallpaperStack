package com.example.wallpaperstack.presentation.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Locale

fun Context.getCustomColor(color:Int): Int {
    return ContextCompat.getColor(this, color)
}
fun Fragment.getCustomColor(color:Int): Int {
    return ContextCompat.getColor(requireContext(), color)
}