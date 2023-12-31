package com.example.recyclerjank.pager.list

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.tracing.Trace
import com.bumptech.glide.request.RequestListener
import com.example.recyclerjank.databinding.ItemListBinding
import com.example.recyclerjank.ui.entity.ListService
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerjank.pager.ViewCache


class ListItemAdapter(
    private val listener: RequestListener<Drawable>,
    config: AsyncDifferConfig<ListService>,
    private val color: Int
) : ListAdapter<ListService, ListItemAdapter.Holder>(config) {
    class Holder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        Trace.beginSection("ListItemCreateView")
//        val holder = Holder(ItemListBinding.inflate(LayoutInflater.from(parent.context))).apply {
//            binding.rootListContainer.setBackgroundColor(color)
//        }
        val holder = ViewCache.getListView(parent.context)
        Trace.endSection()
        return holder
    }

    override fun onViewRecycled(holder: Holder) {
        super.onViewRecycled(holder)
        ViewCache.putListView(holder)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        getItem(position)?.apply {
            holder.binding.tvTitle.text = title
            holder.binding.tvDesc.text = desc
            holder.binding.tvContent.text = content
            Glide.with(holder.binding.ivLogo)
                .load("https://img-blog.csdnimg.cn/20200620183713632.png")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.binding.ivLogo)
        }
    }
}