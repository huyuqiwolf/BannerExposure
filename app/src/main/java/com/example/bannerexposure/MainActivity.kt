package com.example.bannerexposure

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.ArrayBlockingQueue

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager

    private val data = listOf<ColorItem>(
        ColorItem(Color.BLACK, "BLACK---0"),
        ColorItem(Color.BLUE, "BLUE---1"),
        ColorItem(Color.GRAY, "GRAY---2"),
        ColorItem(Color.LTGRAY, "LTGRAY---3"),
        ColorItem(Color.YELLOW, "YELLOW---4"),
        ColorItem(Color.CYAN, "CYAN---5"),
    )

    private val exposureFlow = MutableSharedFlow<ExposureData<ColorItem>>()
    private val queue = ArrayBlockingQueue<ExposureData<ColorItem>>(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.pageMargin = 24
        viewPager.adapter = MyAdapter(data)

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                Log.e("TAG", "selected $position ${position % data.size}")
                lifecycleScope.launch {
                    val index = position % data.size
                    exposureFlow.emit(ExposureData(data = data[index]))
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        viewPager.currentItem = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % data.size)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                exposureFlow.collect {
                    process(it)
                }
            }
        }
    }

    private fun process(exposureData: ExposureData<ColorItem>? = null) {
        if (exposureData == null) {
            // 跳转其他页面触发的曝光
            if (queue.isEmpty()) return
            queue.poll()?.let {
                Log.e("TAG", "跳转导致的曝光：${it} now:${System.currentTimeMillis()}")
                it.apply {
                    inTime = 0L
                }
            }
        } else {
            // 轮播触发的曝光
            if (queue.isEmpty()) {
                queue.put(exposureData)
                return
            }
            queue.poll()?.let {
                if (it.data == exposureData.data) {
                    Log.e("TAG", "数据相同，忽略")
                    return@let
                }
                Log.e("TAG", "轮播导致的曝光：${it} now:${System.currentTimeMillis()}")
                it.apply {
                    inTime = 0L
                }
                queue.put(exposureData)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        process()
    }

    override fun onResume() {
        super.onResume()
        // 从其他页面进来
        process(ExposureData(data = data[viewPager.currentItem % data.size]))
    }

    fun next(view: View) {
        startActivity(Intent(this@MainActivity, SecondActivity::class.java))
    }

    fun sendOnShow(view: View) {
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE).apply {
            putExtra("LIFECYCLE","onShow")
        }
        sendBroadcast(intent)
    }

    fun sendOnHide(view: View) {
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE).apply {
            putExtra("LIFECYCLE","onHide")
        }
        sendBroadcast(intent)
    }

    fun sendNormal(view: View) {
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE).apply {
            this.putExtras(Bundle().apply {
                this.putIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(1,2,3))
            })
        }
        sendBroadcast(intent)
    }
}

data class ExposureData<T>(
    var inTime: Long = System.currentTimeMillis(),
    val data: T
)

data class ColorItem(
    @ColorInt val color: Int,
    val text: String
)

class MyAdapter(private val data: List<ColorItem>) : PagerAdapter() {
    override fun getCount(): Int = Int.MAX_VALUE

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(container.context).inflate(R.layout.page_item, container, false)
        container.addView(view)
        view.findViewById<TextView>(R.id.text).text = getItem(position).text
        view.findViewById<CardView>(R.id.root).setCardBackgroundColor(getItem(position).color)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        (obj as? View)?.let {
            container.removeView(it)
        }
    }

    private fun getItem(position: Int): ColorItem {
        return data[getRealIndex(position)]
    }

    private fun getRealIndex(position: Int): Int {
        return position % data.size
    }
}