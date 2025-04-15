package com.example.wallpaperstack.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wallpaperstack.databinding.BottomsheetItemBinding
import com.example.wallpaperstack.presentation.model.ItemInfo

class BottomSheetAdapter():
    ListAdapter<ItemInfo, BottomSheetViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BottomSheetViewHolder{
        return BottomSheetViewHolder(
            BottomsheetItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: BottomSheetViewHolder,
        position: Int,
    ) {
        val item = getItem(position) ?: return
        holder.binding.tvTitle.text = item.title
        holder.binding.tvInfo.text = item.value
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<ItemInfo>() {

    override fun areItemsTheSame(
        oldItem: ItemInfo,
        newItem: ItemInfo,
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: ItemInfo,
        newItem: ItemInfo,
    ): Boolean {
        return oldItem.value == newItem.value
    }
}
