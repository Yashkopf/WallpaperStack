package com.example.wallpaperstack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Sorting(val value: String) {
    DATE_ADDED("date_added"),
    RANDOM("random"),
    VIEWS("views"),
    FAVORITES("favorites"),
    TOP_LIST("top_list")
}