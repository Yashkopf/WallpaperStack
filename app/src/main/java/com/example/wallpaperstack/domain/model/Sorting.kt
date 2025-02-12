package com.example.wallpaperstack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Sorting(val value: String) {
    TOP_LIST("top_list"),
    DATE_ADDED("date_added"),
    FAVORITES("favorites"),
    RANDOM("random"),
    VIEWS("views")
}