package com.example.recyclerjank.ui.entity

import androidx.annotation.ColorInt

data class IconService(
    val viewType: LayoutType,
    val category: String,
    @ColorInt
    val color: Int,
    val gridServices: List<GridService> = emptyList(),
    val listServices: List<ListService> = emptyList(),
    val bannerServices: List<BannerService> = emptyList(),
    val cardServices: List<CardService> = emptyList(),
)