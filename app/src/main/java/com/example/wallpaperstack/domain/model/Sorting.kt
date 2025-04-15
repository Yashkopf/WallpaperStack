package com.example.wallpaperstack.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Sorting(val value: String) {
    HOT("toplist"),
    RELEVANCE("relevance"),
    FAVORITES("favorites"),
    RANDOM("random"),
    VIEWS("views")
}