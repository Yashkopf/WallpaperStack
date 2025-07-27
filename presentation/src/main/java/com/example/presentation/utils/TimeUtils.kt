package com.example.presentation.utils

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.log10
import kotlin.math.pow

fun formatDate(inputData: String?): String {
    if (inputData == null) return ""
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val date = inputFormat.parse(inputData) ?: Date()
    return outputFormat.format(date)

}

fun formatFileSize(inputData: Long?): String {
    if (inputData == null) return "0"
    val units = arrayOf("B", "kB", "MB", "GB", "TB", "PB", "EB")
    val digit = (log10(inputData.toDouble()) / log10(1024.0)).toInt()
    return DecimalFormat("#,##0.#").format(inputData / 1024.0.pow(digit)) + " " + units[digit]

}
