package com.example.recyclerjank.pager.grid

import androidx.recyclerview.widget.DiffUtil
import com.example.recyclerjank.ui.entity.GridService

object GridServiceDiffCallback :DiffUtil.ItemCallback<GridService>(){
    override fun areItemsTheSame(oldItem: GridService, newItem: GridService): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: GridService, newItem: GridService): Boolean {
        return oldItem == newItem
    }
}