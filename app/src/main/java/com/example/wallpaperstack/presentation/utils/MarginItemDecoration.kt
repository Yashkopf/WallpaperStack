package com.example.wallpaperstack.presentation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val position = parent.getChildAdapterPosition(view)

        val layoutManager = parent.layoutManager as? GridLayoutManager ?: return

        val spanCount = layoutManager.spanCount
        val column = position % spanCount // Item column index

        outRect.left = margin / 2
        outRect.right = margin / 2
        outRect.top = margin / 2
        outRect.bottom = margin / 2

        // Ensure outermost items align with the edges
        if (column == 0) { // First column
            outRect.left = margin
        }
        if (column == spanCount - 1) { // Last column
            outRect.right = margin
        }
    }
}
