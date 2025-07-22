package com.example.wallpaperstack.presentation.features.detail_screen

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wallpaperstack.databinding.FragmentDetailBinding
import com.example.wallpaperstack.domain.model.listWallpapers.WallpapersListDetails
import com.example.wallpaperstack.presentation.features.bottom_sheet_details.BottomSheetDetailsFragment

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private var wallpapers: WallpapersListDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()

        wallpapers = arguments?.getParcelable(DETAIL_FRAGMENT_ENTITY)
        binding?.apply {

            Glide.with(requireContext())
                .load(wallpapers?.path)
                .thumbnail(
                    Glide.with(requireContext())
                        .load(wallpapers?.thumbs?.original)
                        .centerCrop()
                )
                .centerCrop()
                .into(ivWallpaper)

            btnDetailButton.setOnClickListener { v ->
                launchBottomSheetFragment(wallpapers?.id.toString())
            }
        }
    }

    private fun onBackPressed() {
        binding?.btnBack?.setOnClickListener { v ->
            findNavController().popBackStack()
        }
    }

    private fun launchBottomSheetFragment(id: String) {
        BottomSheetDetailsFragment.Companion.create(id).show(parentFragmentManager, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        private const val DETAIL_FRAGMENT_ENTITY = "entity"

        fun makeArgs(item: Parcelable): Bundle {
            return bundleOf(DETAIL_FRAGMENT_ENTITY to item)
        }
    }
}