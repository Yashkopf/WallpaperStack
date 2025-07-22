package com.example.wallpaperstack.data.local.preferences

import com.example.wallpaperstack.data.local.AppPreferences
import com.example.wallpaperstack.domain.preferences.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class PreferencesRepositoryImpl(private val appPreferences: AppPreferences): PreferencesRepository {

    override fun changePurity(purity: String) {
        appPreferences.changePurityState = purity
    }

    override fun getPurity(): String {
        return appPreferences.changePurityState
    }

    override fun saveGeneralCategory(category: Int) {
        appPreferences.generalCategory =  category
    }

    override fun getGeneralCategory(): Int {
        return appPreferences.generalCategory
    }

    override fun saveAnimeCategory(category: Int) {
        appPreferences.animeCategory = category
    }

    override fun getAnimeCategory(): Int {
        return appPreferences.animeCategory
    }

    override fun savePeopleCategory(category: Int) {
        appPreferences.peopleCategory = category
    }

    override fun getPeopleCategory(): Int {
       return appPreferences.peopleCategory
    }
}