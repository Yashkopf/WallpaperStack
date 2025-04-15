package com.example.wallpaperstack.presentation

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wallpaperstack.databinding.FragmentDetailBinding
import com.example.wallpaperstack.domain.model.listWallpapers.WallpaperInfo
import com.example.wallpaperstack.presentation.utils.formatDate

class DetailFragment : Fragment() {

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
        zoomInAnimation(view)

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

            btnDetailButton.setOnClickListener { v ->
                launchBottomSheetFragment(wallpapers?.id.toString())
            }
        }

        binding?.btnBack?.setOnClickListener { v ->
            zoomOutAnimation()
        }
    }

    private fun launchBottomSheetFragment(id: String){
        val args = BottomSheetFragment.makeArgs(id)
        val dialog = BottomSheetFragment().apply {
            this.arguments = args
        }
        dialog.show(this.parentFragmentManager, "entity")
    }

    private fun onBackPressedCallBack() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                zoomOutAnimation()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )
    }

    fun zoomInAnimation(view: View) {
        val pivotX = arguments?.getFloat("pivotX") ?: view.width / 2f
        val pivotY = arguments?.getFloat("pivotY") ?: view.height / 2f

        // Set pivot for zoom animation
        view.pivotX = pivotX
        view.pivotY = pivotY

        // Apply zoom-in animation
        view.scaleX = 0.3f
        view.scaleY = 0.3f
        view.alpha = 0f

        view.animate()
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .setDuration(300)
            .setInterpolator(DecelerateInterpolator())
            .start()
    }

    private fun zoomOutAnimation() {

        view?.animate()
            ?.scaleX(0.6f)
            ?.scaleY(0.6f)
            ?.alpha(0f)
            ?.setDuration(100)
            ?.setInterpolator(DecelerateInterpolator())
            ?.withEndAction {
                findNavController().popBackStack()
            }
            ?.start()
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