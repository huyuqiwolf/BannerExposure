package com.example.recyclerjank.pager.grid

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.tracing.Trace
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.recyclerjank.databinding.ItemGridBinding
import com.example.recyclerjank.pager.ViewCache
import com.example.recyclerjank.ui.entity.GridService

class GridItemAdapter(
    private val listener: RequestListener<Drawable>,
    config: AsyncDifferConfig<GridService>,
    private val color: Int
) : ListAdapter<GridService, GridItemAdapter.Holder>(config) {
    class Holder(val binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        Trace.beginSection("GridItemCreateView")
        val holder =  ViewCache.getGridView(parent.context)
//        Holder(ItemGridBinding.inflate(LayoutInflater.from(parent.context))).apply {
//            binding.rootGridContainer.setBackgroundColor(color)
//        }
        Trace.endSection()
        return holder
    }


    override fun onViewRecycled(holder: Holder) {
        super.onViewRecycled(holder)
        ViewCache.putGridView(holder)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        getItem(position)?.apply {
            holder.binding.tvTitle.text = title
            holder.binding.tvDesc.text = desc
            Glide.with(holder.binding.ivLogo)
                .load("https://www.baidu.com/img/PCfb_5bf082d29588c07f842ccde3f97243ea.png")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(holder.binding.ivLogo)
        }
    }
}