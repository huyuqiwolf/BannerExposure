package com.example.recyclerjank.pager

import androidx.recyclerview.widget.AsyncDifferConfig
import com.example.recyclerjank.pager.grid.GridServiceDiffCallback
import com.example.recyclerjank.pager.list.ListServiceDiffCallback
import com.example.recyclerjank.ui.entity.GridService
import com.example.recyclerjank.ui.entity.ListService

object Constant {
    const val TAG = "ViewPager_"
    val gridDifferConfig = AsyncDifferConfig.Builder<GridService>(GridServiceDiffCallback)
        .build()
    val listDifferConfig = AsyncDifferConfig.Builder<ListService>(ListServiceDiffCallback)
        .build()

}