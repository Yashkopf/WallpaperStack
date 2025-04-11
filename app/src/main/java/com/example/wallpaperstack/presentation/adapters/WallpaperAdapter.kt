package com.example.wallpaperstack.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.example.wallpaperstack.R
import com.example.wallpaperstack.databinding.ItemWallpaperBinding
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.presentation.utils.formatDate
import com.google.android.material.shape.RoundedCornerTreatment

class WallpaperAdapter(
    private val onItemClick: ((WallpaperInfo, View) -> Unit),
    private val onItemLongClick: ((WallpaperInfo) -> Unit),
) :
    PagingDataAdapter<WallpaperInfo, WallpaperViewHolder>(
        WallpaperDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): WallpaperViewHolder {
        return WallpaperViewHolder(
            ItemWallpaperBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: WallpaperViewHolder,
        position: Int,
    ) {
        val wallpaper = getItem(position) ?: return
        holder.binding.apply {
            val view = ivWallpaper.apply {
                setShapeAppearanceModel(
                    this.shapeAppearanceModel
                        .toBuilder().setAllCorners(RoundedCornerTreatment())
                        .setAllCornerSizes { 45f }
                        .build())
                tvFavorites.text = wallpaper.favorites.toString()
                tvCategory.text = formatDate(wallpaper.createdAt.toString())
                root.setOnClickListener { v ->
                    onItemClick(wallpaper, v)
                }
                root.setOnLongClickListener { v ->
                    onItemLongClick(wallpaper)
                    return@setOnLongClickListener true
                }
            }

            val imageScale = RequestOptions()
                .centerCrop()
                .override(680, 1200)

            Glide.with(view.context)
                .load(
                    if (wallpaper.ratio.toFloat() <= 1f) {
                        wallpaper.thumbs.original
                    } else {
                        wallpaper.thumbs.large
                    }
                )
                .transition(withCrossFade())
                .placeholder(R.color.shimmer_background)
                .apply(imageScale)
                .into(view)
        }
    }
}