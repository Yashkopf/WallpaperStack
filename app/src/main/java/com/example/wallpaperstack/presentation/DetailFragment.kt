package com.example.wallpaperstack.presentation

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wallpaperstack.databinding.FragmentDetailBinding
import com.example.wallpaperstack.domain.model.WallpaperInfo

class DetailFragment: Fragment() {

    private var binding: FragmentDetailBinding? = null
    private var wallpapers: WallpaperInfo? = null

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
        onBackPressedCallBack()

        wallpapers = arguments?.getParcelable(DETAIL_FRAGMENT_ENTITY)
        binding?.apply {
            Glide.with(requireContext())
                .load(wallpapers?.path)
                .centerCrop()
                .thumbnail(
                    Glide.with(requireContext())
                        .load(wallpapers?.thumbs?.original)
                        .centerCrop()
                )
                .into(ivWallpaper)
        }
    }

    private fun onBackPressedCallBack() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        private const val DETAIL_FRAGMENT_ENTITY = "entity"

        fun makeArgs(id: Parcelable): Bundle {
            return bundleOf(DETAIL_FRAGMENT_ENTITY to id)
        }
    }
}