package com.example.wallpaperstack.presentation

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperstack.R
import com.example.wallpaperstack.databinding.BottomsheetFragmentBinding
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.domain.model.Wallpapers
import com.example.wallpaperstack.presentation.adapters.BottomSheetAdapter
import com.example.wallpaperstack.presentation.model.ItemInfo
import com.example.wallpaperstack.presentation.utils.formatDate
import com.example.wallpaperstack.presentation.utils.formatFileSize
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment: BottomSheetDialogFragment() {

    private var binding: BottomsheetFragmentBinding? = null
    private var wallpapers: WallpaperInfo? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: BottomSheetAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomsheetFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wallpapers = arguments?.getParcelable(BOTTOM_SHEET_FRAGMENT_ENTITY)
        recyclerView = binding?.rvBottomSheetDialog
        adapter = BottomSheetAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = adapter
        adapter?.submitList(
            listOf<ItemInfo>(
                ItemInfo(
                    title = "Category",
                    value = wallpapers?.category.toString()
                ),
                ItemInfo(
                    title = "Dimension",
                    value = wallpapers?.resolution.toString()
                ),
                ItemInfo(
                    title = "Date added",
                    value = formatDate(wallpapers?.createdAt)
                ),
                ItemInfo(
                    title = "Favorites",
                    value = wallpapers?.favorites.toString()
                ),
                ItemInfo(
                    title = "Views",
                    value = wallpapers?.views.toString()
                ),
                ItemInfo(
                    title = "File size",
                    value = formatFileSize(wallpapers?.fileSize?.toLong())
                )
            )
        )
//        binding?.tvUserName?.text = wallpapers?.uploader?.userName
    }

    companion object {

        private const val BOTTOM_SHEET_FRAGMENT_ENTITY = "entity"

        fun makeArgs(id: Parcelable): Bundle {
            return bundleOf(BOTTOM_SHEET_FRAGMENT_ENTITY to id)
        }
    }
}