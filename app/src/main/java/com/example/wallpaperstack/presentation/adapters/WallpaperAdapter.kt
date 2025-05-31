package com.example.wallpaperstack.presentation.adapters

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.example.wallpaperstack.R
import com.example.wallpaperstack.databinding.ItemWallpaperBinding
import com.example.wallpaperstack.domain.model.listWallpapers.WallpapersListDetails
import com.example.wallpaperstack.presentation.utils.formatDate
import com.google.android.material.shape.RoundedCornerTreatment

class WallpaperAdapter(
    private val onItemClick: ((Parcelable) -> Unit),
    private val onItemLongClick: ((String) -> Unit),
) :
    PagingDataAdapter<WallpapersListDetails, WallpaperViewHolder>(
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
        val binding = holder.binding
        val wallpaper = getItem(position) ?: return
        val view = binding.ivWallpaper.apply {
            setShapeAppearanceModel(
                this.shapeAppearanceModel
                    .toBuilder().setAllCorners(RoundedCornerTreatment())
                    .setAllCornerSizes { 45f }
                    .build())
            //                if(this.context.resources.displayMetrics.widthPixels == 1200)
        }

        binding.tvFavorites.text = wallpaper.favorites.toString()
        binding.tvCategory.text = formatDate(wallpaper.createdAt.toString())

        binding.root.setOnClickListener {
            onItemClick(wallpaper)
        }
        binding.root.setOnLongClickListener {
            onItemLongClick(wallpaper.id)
            return@setOnLongClickListener true
        }

        val imageScale = RequestOptions()
            .centerCrop()
            .override(720, 1280)

        Glide.with(view.context)
            .load(
                if (wallpaper.ratio.toFloat() <= 1f) {
                    wallpaper.thumbs?.original
                } else {
                    wallpaper.thumbs?.large
                }
            )
            .transition(withCrossFade())
            .placeholder(R.color.shimmer_background)
//                .apply(imageScale)
            .centerCrop()
            .into(view)
    }
}