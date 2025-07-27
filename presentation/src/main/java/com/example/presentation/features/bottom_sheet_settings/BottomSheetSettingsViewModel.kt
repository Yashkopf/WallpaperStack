package com.example.presentation.features.bottom_sheet_settings

import androidx.lifecycle.ViewModel
import com.example.domain.preferences.PreferencesRepository
import com.example.presentation.model.PurityValue
import com.example.presentation.utils.Empty
import kotlinx.coroutines.flow.MutableStateFlow

internal class BottomSheetSettingsViewModel(
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val purity = MutableStateFlow(preferencesRepository.getPurity())
    private val generalCategoryState = MutableStateFlow(preferencesRepository.getGeneralCategory())
    private val animeCategoryState = MutableStateFlow(preferencesRepository.getAnimeCategory())
    private val peopleCategoryState = MutableStateFlow(preferencesRepository.getPeopleCategory())

    fun switchButtonState(): Boolean {
        return purity.value == PurityValue.PURITY_ENABLED.value
    }

    fun changePurity(purity: String) {
        preferencesRepository.changePurity(purity)
    }

    fun saveGeneralButtonState(value: Int) {
        preferencesRepository.saveGeneralCategory(value)
    }

    fun saveAnimeButtonState(value: Int) {
        preferencesRepository.saveAnimeCategory(value)
    }

    fun savePeopleButtonState(value: Int) {
        preferencesRepository.savePeopleCategory(value)
    }

    fun getPrefValues(index: Int): Int {
        val prefs = listOf(
            generalCategoryState.value,
            animeCategoryState.value,
            peopleCategoryState.value
        )
        return prefs[index]
    }

    fun categoryNumber(
        checkedId: Int,
        isChecked: Boolean,
        generalButton: Int,
        animeButton: Int,
        peopleButton: Int,
    ): String {
        when (checkedId) {
            generalButton -> {
                generalCategoryState.value = getCategoryId(isChecked)
                saveGeneralButtonState(generalCategoryState.value)
            }

            animeButton -> {
                animeCategoryState.value = getCategoryId(isChecked)
                saveAnimeButtonState(animeCategoryState.value)
            }

            peopleButton -> {
                peopleCategoryState.value = getCategoryId(isChecked)
                savePeopleButtonState(peopleCategoryState.value)
            }
        }
        return "${generalCategoryState.value}${animeCategoryState.value}${peopleCategoryState.value}"
    }

    private fun getCategoryId(isChecked: Boolean): Int {
        return if (isChecked) 1 else Empty.INT
    }
}