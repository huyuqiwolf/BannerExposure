package com.example.recyclerjank.ui.entity

import android.content.Context

sealed class LayoutType(
    val itemViewType: Int,
    val itemHeight: Int,
    val rows: Int,
    val colums: Int,
    val titleHeight: Int = 48,
    val space: Int = 8
) {
    object Grid : LayoutType(0, itemHeight = 72, rows = 2, colums = 2)
    object List : LayoutType(1, itemHeight = 80, rows = 3, colums = 1)

    object Card : LayoutType(2, itemHeight = 160, rows = 1, colums = 1)

    object Banner : LayoutType(3, itemHeight = 160, rows = 1, colums = 1)
}

fun LayoutType.getContentHeight(context: Context): Int {
    return context.dp2px(itemHeight * rows + space)
}

fun Context.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (scale * (dp)).toInt()
}