package com.example.wallpaperstack.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.domain.model.Wallpapers

class WallpaperDiffCallback: DiffUtil.ItemCallback<WallpaperInfo>() {

    override fun areItemsTheSame(
        oldItem: WallpaperInfo,
        newItem: WallpaperInfo,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WallpaperInfo,
        newItem: WallpaperInfo,
    ): Boolean {
        return oldItem.thumbs.original == newItem.thumbs.original
    }
}