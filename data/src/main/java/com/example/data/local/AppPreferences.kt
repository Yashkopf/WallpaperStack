package com.example.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

internal class AppPreferences(private val sharedPref: SharedPreferences) {

    var changePurityState: String
        get() = sharedPref.getString(PURITY_CONTENT, PURITY_CONTENT_DEFAULT) ?: PURITY_CONTENT_DEFAULT
        set(value) = sharedPref.edit { putString(PURITY_CONTENT, value) }

    var generalCategory: Int
        get() = sharedPref.getInt(GENERAL_CATEGORY, GENERAL_CATEGORY_DEFAULT)
        set(value) = sharedPref.edit { putInt(GENERAL_CATEGORY, value) }

    var animeCategory: Int
        get() = sharedPref.getInt(ANIME_CATEGORY, ANIME_CATEGORY_DEFAULT)
        set(value) = sharedPref.edit { putInt(ANIME_CATEGORY, value) }

    var peopleCategory: Int
        get() = sharedPref.getInt(PEOPLE_CATEGORY, PEOPLE_CATEGORY_DEFAULT)
        set(value) = sharedPref.edit { putInt(PEOPLE_CATEGORY, value) }

    companion object {
        const val PEOPLE_CATEGORY = "people_category"
        const val PEOPLE_CATEGORY_DEFAULT = 0
        const val ANIME_CATEGORY = "anime_category"
        const val ANIME_CATEGORY_DEFAULT = 0
        const val GENERAL_CATEGORY = "general_category"
        const val GENERAL_CATEGORY_DEFAULT = 0

        const val PURITY_CONTENT = "purity_content"
        const val PURITY_CONTENT_DEFAULT = "100"
    }
}