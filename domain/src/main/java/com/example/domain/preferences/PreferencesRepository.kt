package com.example.domain.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    fun changePurity(purity: String)
    fun getPurity(): String

    fun saveGeneralCategory(category: Int)
    fun getGeneralCategory(): Int

    fun saveAnimeCategory(category: Int)
    fun getAnimeCategory(): Int

    fun savePeopleCategory(category: Int)
    fun getPeopleCategory(): Int
}