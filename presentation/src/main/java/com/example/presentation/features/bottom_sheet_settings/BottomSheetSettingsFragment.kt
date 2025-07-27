package com.example.presentation.features.bottom_sheet_settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.databinding.BottomsheetSettingsFragmentBinding
import com.example.presentation.model.PurityValue
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class BottomSheetSettingsFragment(
    val onChangePurity: (String) -> Unit,
    val onChangeCategory: (String) -> Unit,
) : BottomSheetDialogFragment() {

    private var binding: BottomsheetSettingsFragmentBinding? = null
    private val viewModel: BottomSheetSettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomsheetSettingsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adultContentSwitchButton()
        categoryChangeToggleButton()
        initialToggleButtons()
    }

    private fun initialToggleButtons() {
        val buttonsList = listOf(
            binding?.btnGeneral?.id,
            binding?.btnAnime?.id,
            binding?.btnPeople?.id
        )

        buttonsList.forEachIndexed { index, button ->
            val stateValue = viewModel.getPrefValues(index)
            if (stateValue == ACTIVE_STATE && button != null) checkToggleButton(button)
        }
    }

    private fun checkToggleButton(buttonId: Int){
        binding?.tgCategory?.check(
            buttonId
        )
    }

    private fun categoryChangeToggleButton() {
        binding?.let { binding ->
            binding.tgCategory.addOnButtonCheckedListener { _, checkedId, isChecked ->
                val categoryNumber = viewModel.categoryNumber(
                    checkedId = checkedId,
                    isChecked = isChecked,
                    generalButton = binding.btnGeneral.id,
                    animeButton = binding.btnAnime.id,
                    peopleButton = binding.btnPeople.id
                )
                onChangeCategory(categoryNumber)
            }
        }
    }

    private fun adultContentSwitchButton() {
        val switchButton = binding?.swcAdultContent
        switchButton?.isChecked = viewModel.switchButtonState()
        switchButton?.setOnClickListener { v ->
            val purity = if (switchButton.isChecked) PurityValue.PURITY_ENABLED.value
            else PurityValue.PURITY_DISABLED.value
            onChangePurity(purity)
            viewModel.changePurity(purity)
        }
    }

    companion object {
        const val ACTIVE_STATE = 1
    }
}
