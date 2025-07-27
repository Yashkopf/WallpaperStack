package com.example.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.listWallpapers.WallpapersListDetails

internal class WallpaperDiffCallback : DiffUtil.ItemCallback<WallpapersListDetails>() {

    override fun areItemsTheSame(
        oldItem: WallpapersListDetails,
        newItem: WallpapersListDetails,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WallpapersListDetails,
        newItem: WallpapersListDetails,
    ): Boolean {
        return oldItem.thumbs?.original == newItem.thumbs?.original
                && oldItem.favorites == newItem.favorites
                && oldItem.createdAt == newItem.createdAt
    }
}