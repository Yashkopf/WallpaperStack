package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Sorting(val value: String) {
    DATE_ADDED("date_added"),
    HOT("toplist"),
    FAVORITES("favorites"),
    RANDOM("random"),
    VIEWS("views")
}