package com.example.recyclerjank.pager

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerjank.ui.entity.dp2px

object GridItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val size = view.context.dp2px(4)
        when (parent.getChildLayoutPosition(view)) {
            0 -> outRect.set(0, 0, size, size)
            1 -> outRect.set(size, 0, 0, size)
            2 -> outRect.set(0, size, size, 0)
            3 -> outRect.set(size, size, 0, 0)
        }
    }
}