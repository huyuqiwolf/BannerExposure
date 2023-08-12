package com.example.recyclerjank.pager.grid

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.tracing.Trace
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.recyclerjank.databinding.LayoutPagerGridBinding
import com.example.recyclerjank.ui.entity.GridService
import com.example.recyclerjank.pager.Constant
import com.example.recyclerjank.pager.GridItemDecoration
import com.example.recyclerjank.ui.entity.LayoutType

private const val TAG = "${Constant.TAG}Grid:"

class GridPagerAdapter(private val services: List<GridService>, private val color: Int) :
    PagerAdapter() {
    companion object {
        private const val PAGE_SIZE = 4
    }

    override fun getCount(): Int = if (services.isEmpty()) 0 else {
        kotlin.math.ceil(1.0 * services.size / PAGE_SIZE).toInt()
    }

    override fun isViewFromObject(view: View, obj: Any) = view === obj

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Trace.beginSection("GridCreatePager")
        val binding = LayoutPagerGridBinding.inflate(LayoutInflater.from(container.context))
        binding.recyclerView.layoutManager = GridLayoutManager(
            container.context,
            LayoutType.Grid.colums,
            GridLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.addItemDecoration(GridItemDecoration)
        binding.recyclerView.adapter =
            GridItemAdapter(GridListener, Constant.gridDifferConfig, color).apply {
                submitList(getData(position))
            }
        container.addView(binding.root)
        Log.e(TAG, "instantiateItem: $position ${services[position]}")
        Trace.endSection()
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        if (obj is View) {
            container.removeView(obj)
            Log.e(TAG, "destroyItem: $position ${services[position]}")
        }
    }

    private fun getData(position: Int): List<GridService> {
        val start = position * PAGE_SIZE
        val end = position * PAGE_SIZE + PAGE_SIZE
        return services.subList(start, end)
    }

}

object GridListener : RequestListener<Drawable> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        Log.e(TAG, "onLoadFailed: ${e?.message}")
        return true
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        Log.e(TAG, "onResourceReady: ")
        return true
    }

}