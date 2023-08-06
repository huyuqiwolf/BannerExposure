package com.example.recyclerjank.pager

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerjank.ui.entity.dp2px

object ListItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val size = view.context.dp2px(4)
        when (parent.getChildLayoutPosition(view)) {
            1 -> outRect.set(0, size, 0, size)
        }
    }
}