package com.example.recyclerjank.pager.banner

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.recyclerjank.ui.entity.BannerService
import com.example.recyclerjank.databinding.LayoutPagerBannerBinding
import com.example.recyclerjank.pager.Constant

private const val TAG = "${Constant.TAG}Banner:"

class BannerPagerAdapter(private val services: List<BannerService>) : PagerAdapter() {

    override fun getCount(): Int = services.size

    override fun isViewFromObject(view: View, obj: Any) = view === obj

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = LayoutPagerBannerBinding.inflate(LayoutInflater.from(container.context))
        container.addView(binding.root)
        Glide.with(binding.ivBanner).load(services[position].bannerUrl.url)
//            .listener(BannerLoadListener)
            .into(binding.ivBanner)
        Log.e(TAG, "instantiateItem: $position ${services[position]}")
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        if (obj is View) {
            container.removeView(obj)
            Log.e(TAG, "destroyItem: $position ${services[position]}")
        }
    }

}

object BannerLoadListener : RequestListener<Drawable> {
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
