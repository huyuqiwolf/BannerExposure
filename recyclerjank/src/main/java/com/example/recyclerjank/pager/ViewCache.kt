package com.example.recyclerjank.pager

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerjank.databinding.ItemGridBinding
import com.example.recyclerjank.databinding.ItemListBinding
import com.example.recyclerjank.databinding.LayoutPagerGridBinding
import com.example.recyclerjank.databinding.LayoutPagerListBinding
import com.example.recyclerjank.pager.grid.GridItemAdapter
import com.example.recyclerjank.pager.grid.GridPagerAdapter
import com.example.recyclerjank.pager.list.ListItemAdapter
import com.example.recyclerjank.ui.entity.LayoutType
import java.util.LinkedList

object ViewCache {

    private val gridCache = LinkedList<GridItemAdapter.Holder>()

    fun getGridView(context: Context): GridItemAdapter.Holder {
        return gridCache.poll() ?: GridItemAdapter.Holder(
            ItemGridBinding.inflate(LayoutInflater.from(context))
        ).apply {
//            Log.e("ViewCache","create new grid item")
        }
    }

    fun putGridView(holder: GridItemAdapter.Holder) {
        gridCache.push(holder)
//        Log.e("ViewCache","push recycler grid item")
    }

    private val gridLayoutCache = LinkedList<LayoutPagerGridBinding>()
    fun getGridLayout(context: Context): LayoutPagerGridBinding {
        return gridLayoutCache.poll() ?: LayoutPagerGridBinding.inflate(
            LayoutInflater.from(context)
        ).apply {
            this.root.tag = this
            this.recyclerView.addItemDecoration(GridItemDecoration)
            this.recyclerView.layoutManager = GridLayoutManager(
                context,
                LayoutType.Grid.colums,
                GridLayoutManager.VERTICAL,
                false
            )
            Log.e("ViewCache", "create grid layout pager")
        }
    }

    fun putGridLayout(binding: LayoutPagerGridBinding) {
        gridLayoutCache.push(binding)
        Log.e("ViewCache", "put grid layout ")
    }

    private val listLayoutCache = LinkedList<LayoutPagerListBinding>()

    fun getListLayout(context: Context): LayoutPagerListBinding {
        return listLayoutCache.poll() ?: LayoutPagerListBinding.inflate(
            LayoutInflater.from(context)
        ).apply {
            this.recyclerView.addItemDecoration(ListItemDecoration)
            this.recyclerView.layoutManager = GridLayoutManager(
                context,
                LayoutType.List.colums,
                GridLayoutManager.VERTICAL,
                false
            )
            this.root.tag = this
            Log.e("ViewCache", "create list layout pager")
        }
    }

    fun putListLayout(binding: LayoutPagerListBinding) {
        listLayoutCache.push(binding)
        Log.e("ViewCache", "put list layout ")
    }

    private val listCache = LinkedList<ListItemAdapter.Holder>()
    fun getListView(context: Context): ListItemAdapter.Holder {
        return listCache.poll() ?: ListItemAdapter.Holder(
            ItemListBinding.inflate(LayoutInflater.from(context))
        )
    }

    fun putListView(holder: ListItemAdapter.Holder) {
        listCache.push(holder)
        Log.e("ViewCache", "put list item ")
    }

    fun clear() {
        listCache.clear()
        listLayoutCache.clear()
        gridCache.clear()
        gridLayoutCache.clear()
    }


//    fun getListView():ListItemAdapter.Holder{
//
//    }
}