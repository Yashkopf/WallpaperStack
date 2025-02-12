package com.example.wallpaperstack.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.wallpaperstack.R
import com.example.wallpaperstack.databinding.ItemWallpaperBinding
import com.example.wallpaperstack.domain.model.WallpaperInfo
import com.example.wallpaperstack.presentation.utils.formatDate
import com.google.android.material.shape.RoundedCornerTreatment
import java.text.SimpleDateFormat
import java.util.Locale

class WallpaperAdapter(private val onItemClick: (WallpaperInfo) -> Unit) :
    PagingDataAdapter<WallpaperInfo, WallpaperViewHolder>(
        WallpaperDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): WallpaperViewHolder = WallpaperViewHolder(
        ItemWallpaperBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

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
                    onItemClick(wallpaper)
                }
            }
            Glide.with(view.context)
                .load(wallpaper.thumbs.original)
                .transition(withCrossFade())
                .placeholder(R.color.select_button_state)
                .into(view)
        }
    }
}