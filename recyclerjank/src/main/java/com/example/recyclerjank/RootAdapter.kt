package com.example.recyclerjank

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.recyclerjank.databinding.LayoutBannerBinding
import com.example.recyclerjank.databinding.LayoutCardBinding
import com.example.recyclerjank.databinding.LayoutGridBinding
import com.example.recyclerjank.databinding.LayoutListBinding
import com.example.recyclerjank.pager.banner.BannerHolder
import com.example.recyclerjank.pager.banner.BannerPagerAdapter
import com.example.recyclerjank.pager.card.CardHolder
import com.example.recyclerjank.pager.card.CardPagerAdapter
import com.example.recyclerjank.pager.grid.GridHolder
import com.example.recyclerjank.pager.grid.GridPagerAdapter
import com.example.recyclerjank.pager.list.ListHolder
import com.example.recyclerjank.pager.list.ListPagerAdapter
import com.example.recyclerjank.ui.entity.IconService
import com.example.recyclerjank.ui.entity.LayoutType
import com.example.recyclerjank.ui.entity.getContentHeight
import com.example.recyclerjank.ui.ext.applyHeight

class RootAdapter(differ: AsyncDifferConfig<IconService>) :
    ListAdapter<IconService, RecyclerView.ViewHolder>(differ) {

    private val cachedLayoutManager = hashMapOf<LayoutType, LayoutManager>()

    private fun getLayoutManager(layoutType: LayoutType, context: Context): LayoutManager {
        return cachedLayoutManager[layoutType] ?: GridLayoutManager(context, layoutType.colums)
    }


    @Throws
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInterface = LayoutInflater.from(parent.context);
        return when (viewType) {
            LayoutType.List.itemViewType -> ListHolder(
                LayoutListBinding.inflate(
                    layoutInterface,
                    parent,
                    false
                ).apply {
                    viewPager.applyHeight {
                        LayoutType.List.getContentHeight(it).apply {
                            Log.e("TAG", "dd $this")
                        }
                    }
                }
            )

            LayoutType.Grid.itemViewType -> GridHolder(
                LayoutGridBinding.inflate(
                    layoutInterface,
                    parent,
                    false
                ).apply { viewPager.applyHeight { LayoutType.Grid.getContentHeight(it) } }
            )

            LayoutType.Card.itemViewType -> CardHolder(
                LayoutCardBinding.inflate(
                    layoutInterface,
                    parent,
                    false
                ).apply { viewPager.applyHeight { LayoutType.Card.getContentHeight(it) } }
            )

            LayoutType.Banner.itemViewType -> BannerHolder(
                LayoutBannerBinding.inflate(
                    layoutInterface,
                    parent,
                    false
                ).apply { viewPager.applyHeight { LayoutType.Banner.getContentHeight(it) } }
            )

            else -> throw IllegalArgumentException("不支持的布局类型")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListHolder -> bindListHolder(holder.binding, getItem(position))
            is GridHolder -> bindGridHolder(holder.binding, getItem(position))
            is BannerHolder -> bindBannerHolder(holder.binding, getItem(position))
            is CardHolder -> bindCardHolder(holder.binding, getItem(position))
            else -> return
        }
    }

    private fun bindCardHolder(binding: LayoutCardBinding, service: IconService) {
//        binding.viewPager.background =
//            ContextCompat.getDrawable(binding.root.context, R.drawable.item_layout_bg)?.apply {
//                (this as? GradientDrawable)?.setColor(service.color)
//            }
        binding.tvTitle.text = HtmlCompat.fromHtml(
            "${service.category} &lt;&lt;${service.cardServices.size}&gt;&gt;",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        binding.viewPager.adapter = CardPagerAdapter(service.cardServices)
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.pageMargin =
            binding.root.context.resources.getDimensionPixelOffset(R.dimen.margin)
    }

    private fun bindBannerHolder(
        binding: LayoutBannerBinding,
        service: IconService
    ) {
//        binding.viewPager.background =
//            ContextCompat.getDrawable(binding.root.context, R.drawable.item_layout_bg)?.apply {
//                (this as? GradientDrawable)?.setColor(service.color)
//            }
        binding.tvTitle.text =
            HtmlCompat.fromHtml(
                "${service.category} &lt;&lt;${service.bannerServices.size}&gt;&gt;",
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        binding.viewPager.adapter = BannerPagerAdapter(service.bannerServices)
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.pageMargin =
            binding.root.context.resources.getDimensionPixelOffset(R.dimen.margin)
    }

    private fun bindGridHolder(binding: LayoutGridBinding, service: IconService) {
//        binding.viewPager.background =
//            ContextCompat.getDrawable(binding.root.context, R.drawable.item_layout_bg)?.apply {
//                (this as? GradientDrawable)?.setColor(service.color)
//            }
        binding.tvTitle.text = HtmlCompat.fromHtml(
            "${service.category} &lt;&lt;${service.gridServices.size}&gt;&gt;",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        binding.viewPager.adapter = GridPagerAdapter(service.gridServices, service.color)
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.pageMargin =
            binding.root.context.resources.getDimensionPixelOffset(R.dimen.margin)
    }

    private fun bindListHolder(binding: LayoutListBinding, service: IconService) {
//        binding.viewPager.background =
//            ContextCompat.getDrawable(binding.root.context, R.drawable.item_layout_bg)?.apply {
//                (this as? GradientDrawable)?.setColor(service.color)
//            }

        binding.tvTitle.text = HtmlCompat.fromHtml(
            "${service.category} &lt;&lt;${service.listServices.size}&gt;&gt;",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        binding.viewPager.adapter = ListPagerAdapter(service.listServices, service.color)
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.pageMargin =
            binding.root.context.resources.getDimensionPixelOffset(R.dimen.margin)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.itemViewType
    }
}