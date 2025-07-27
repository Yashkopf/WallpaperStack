package com.example.presentation.features.bottom_sheet_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.adapters.BottomSheetAdapter
import com.example.presentation.databinding.BottomsheetDetailsFragmentBinding
import com.example.presentation.model.ItemInfo
import com.example.presentation.utils.formatDate
import com.example.presentation.utils.formatFileSize
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.RoundedCornerTreatment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class BottomSheetDetailsFragment : BottomSheetDialogFragment() {

    private var binding: BottomsheetDetailsFragmentBinding? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: BottomSheetAdapter? = null
    private val viewModel: BottomSheetFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomsheetDetailsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    private fun initObservers() {

        recyclerView = binding?.rvBottomSheetDialog
        adapter = BottomSheetAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter

        val id = arguments?.getString(BOTTOM_SHEET_WALLPAPER_ID)
        viewModel.getWallpaperInfo(id)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.wallpapersStateFlow.collectLatest { value ->

                binding?.apply {

                    val view = ivUserAvatar.apply {
                        setShapeAppearanceModel(
                            this.shapeAppearanceModel
                                .toBuilder().setAllCorners(RoundedCornerTreatment())
                                .setAllCornerSizes(45f)
                                .build()
                        )
                    }
                    tvUserName.text = value?.uploader?.userName
                    Glide.with(requireContext())
                        .load(value?.uploader?.avatar?.middle)
                        .into(view)
                }

                adapter?.submitList(
                    listOf<ItemInfo>(
                        ItemInfo(
                            title = "Category",
                            value = value?.category.toString()
                        ),
                        ItemInfo(
                            title = "Dimension",
                            value = value?.resolution.toString()
                        ),
                        ItemInfo(
                            title = "Date added",
                            value = formatDate(value?.createdAt)
                        ),
                        ItemInfo(
                            title = "Favorites",
                            value = value?.favorites.toString()
                        ),
                        ItemInfo(
                            title = "Views",
                            value = value?.views.toString()
                        ),
                        ItemInfo(
                            title = "File size",
                            value = formatFileSize(value?.fileSize?.toLong())
                        )
                    )
                )
            }
        }
    }

    companion object {

        private const val BOTTOM_SHEET_WALLPAPER_ID = "BOTTOM_SHEET_WALLPAPER_ID"

        fun create(id: String) =
            BottomSheetDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(BOTTOM_SHEET_WALLPAPER_ID, id)
                }
            }
    }
}