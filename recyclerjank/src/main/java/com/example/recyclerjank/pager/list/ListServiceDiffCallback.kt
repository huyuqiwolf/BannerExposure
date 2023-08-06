package com.example.recyclerjank.pager.list

import androidx.recyclerview.widget.DiffUtil
import com.example.recyclerjank.ui.entity.ListService

object ListServiceDiffCallback :DiffUtil.ItemCallback<ListService>(){
    override fun areItemsTheSame(oldItem: ListService, newItem: ListService): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ListService, newItem: ListService): Boolean {
        return oldItem == newItem
    }
}