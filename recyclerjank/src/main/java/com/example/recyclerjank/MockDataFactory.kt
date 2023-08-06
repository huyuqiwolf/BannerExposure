package com.example.recyclerjank

import android.content.Context
import android.graphics.Color
import android.os.SystemClock
import com.example.recyclerjank.ui.entity.BannerService
import com.example.recyclerjank.ui.entity.CardService
import com.example.recyclerjank.ui.entity.GridService
import com.example.recyclerjank.ui.entity.IconService
import com.example.recyclerjank.ui.entity.LayoutType
import com.example.recyclerjank.ui.entity.ListService
import com.example.recyclerjank.ui.entity.RemoteImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.charset.Charset
import java.util.UUID
import kotlin.math.abs
import kotlin.random.Random

object MockDataFactory {

    private val random = Random(SystemClock.elapsedRealtime())
    private val chars = arrayListOf<Char>()

    // list 内部的大小是12
    val urlsSource = arrayListOf<String>()

    val iconsSource = arrayListOf<String>()

    val urls = arrayListOf<RemoteImage>()
    val icons = arrayListOf<RemoteImage>()
    val addedIcons = arrayListOf<RemoteImage>()
    val addedUrls = arrayListOf<RemoteImage>()

    // https://desk.zol.com.cn/showpic/1280x800_120275_498.html

    //https://desk.zol.com.cn/showpic/1024x768_120390_102.html


    // region
    val bannerUrls = mutableListOf<List<String>>(
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120275_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120276_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120277_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120278_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120279_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120280_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120281_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120282_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120283_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120284_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120284_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120286_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120287_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120288_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120289_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120290_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120291_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120292_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120293_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120294_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120295_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120296_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120297_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120298_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120299_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120300_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120301_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120302_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120303_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120304_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120305_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120306_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120307_498.html"
//        ),
//        listOf(
//            "https://desk.zol.com.cn/showpic/1280x800_120308_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120309_498.html",
//            "https://desk.zol.com.cn/showpic/1280x800_120310_498.html"
//        ),
    )
    //endregion

//    private val addedUrls = mutableSetOf<String>()
//    private val addedIcons = mutableListOf<String>()

    init {
        for (i in 32..126) {
            chars.add(i.toChar())
        }

        for (i in 1..360) {
            urlsSource.add("https://desk.zol.com.cn/showpic/1280x800_${120000 + i}_498.html")
        }

        for (i in 1..360) {
            iconsSource.add("https://desk.zol.com.cn/showpic/1280x800_${120000 + i}_102.html")
        }


    }

    fun getData(context: Context): MutableList<IconService> {
        if (icons.isEmpty()) {
            loadIcons(context)
        }
        if (urls.isEmpty()) {
            loadUrls(context)
        }
        addedIcons.clear()
        addedIcons.clear()
        return mutableListOf<IconService>().apply {
            for (i in 0 until 20) {
                add(randomIconService(i))
//                add(generateGrid(LayoutType.Grid))
//                add(generateList(LayoutType.List))
//                add(generateBanner(LayoutType.Banner))
//                add(generateCard(LayoutType.Card))
            }
        }
    }

    private fun loadIcons(context: Context) {
        val json = context.assets.open("icons.json").bufferedReader().readText()
        val typeToken = object : TypeToken<List<RemoteImage>>() {}.type
        val list: List<RemoteImage> = Gson().fromJson(json, typeToken)
        list.forEach {
            if (it.url.contains("https")) {
                icons.add(it)
            }
        }
    }

    private fun loadUrls(context: Context) {
        val json = context.assets.open("urls.json").bufferedReader().readText()
        val typeToken = object : TypeToken<List<RemoteImage>>() {}.type
        val list: List<RemoteImage> = Gson().fromJson(json, typeToken)
        list.forEach {
            if (it.url.contains("https")) {
                urls.add(it)
            }
        }
    }

    private fun randomIconService(i: Int): IconService {
        val layoutType = randomLayoutType()

        return when (layoutType) {
            LayoutType.List -> generateList(layoutType)
            LayoutType.Grid -> generateGrid(layoutType)
            LayoutType.Banner -> generateBanner(layoutType)
            else -> generateCard(layoutType)
        }
    }

    private fun randomUrl(): RemoteImage {
        var url = urls.random()
        while (addedUrls.contains(url)) {
            url = urls.random()
        }
        addedUrls.add(url)
        return url
    }

    private fun randomIcon(): RemoteImage {
        var url = icons.random()
        while (addedIcons.contains(url)) {
            url = icons.random()
        }
        addedIcons.add(url)
        return url
    }

    private fun generateCard(layoutType: LayoutType): IconService {
        val list = arrayListOf<CardService>()
        for (i in 1..3) {
            list.add(randomCardService(randomUrl()))
        }
        return IconService(
            viewType = layoutType,
            category = "CardType:${random.nextInt()}",
            color = randomColor(),
            cardServices = list
        )
    }

    private fun generateBanner(layoutType: LayoutType): IconService {
        val list = arrayListOf<BannerService>()
        for (i in 1..3) {
            list.add(randomBannerService(randomUrl()))
        }
        return IconService(
            viewType = layoutType,
            category = "BannerType:${random.nextInt()}",
            color = randomColor(),
            bannerServices = list
        )
    }

    private fun generateGrid(layoutType: LayoutType): IconService {
        val list = arrayListOf<GridService>()
        for (i in 1..12) {
            list.add(randomGridService(randomIcon()))
        }
        return IconService(
            viewType = layoutType,
            category = "GridLayout:${random.nextInt()}",
            color = randomColor(),
            gridServices = list
        )
    }

    private fun generateList(layoutType: LayoutType): IconService {
        val list = arrayListOf<ListService>()
        for (i in 1..9) {
            list.add(randomListService(randomIcon()))
        }
        return IconService(
            viewType = layoutType,
            category = "ListLayout:${random.nextInt()}",
            color = randomColor(),
            listServices = list
        )
    }

    private fun randomCardService(url: RemoteImage): CardService {
        return CardService(
            uuid = UUID.randomUUID(),
            cardUrl = url
        )
    }

    private fun randomBannerService(url: RemoteImage): BannerService {
        return BannerService(
            uuid = UUID.randomUUID(),
            bannerUrl = url
        )
    }

    private fun randomListService(url: RemoteImage): ListService {
        return ListService(
            uuid = UUID.randomUUID(),
            logoUrl = url,
            title = "ListService->${randomText(10, true)}",
            desc = "Desc: ${randomText(20)}",
            content = "Content: ${randomText(100)}"
        )
    }

    private fun randomGridService(url: RemoteImage): GridService {
        return GridService(
            uuid = UUID.randomUUID(),
            logoUrl = url,
            title = "GridService->${randomText(10, true)}",
            desc = "Desc: ${randomText(20)}"
        )
    }

    private fun randomText(size: Int, fixSize: Boolean = false): String {
        return buildString {
            if (fixSize) {
                for (i in 1..size) {
                    append(chars[random.nextInt(chars.size)])
                }
            } else {
                val count = random.nextInt(size)
                for (i in 1..count) {
                    append(chars[random.nextInt(chars.size)])
                }
            }
        }
    }


    private fun randomLayoutType(): LayoutType {
        return when (abs(random.nextInt()) % 4) {
            LayoutType.List.itemViewType -> LayoutType.List
            LayoutType.Grid.itemViewType -> LayoutType.Grid
            LayoutType.Banner.itemViewType -> LayoutType.Banner
            else -> LayoutType.Card
        }
    }

    private fun randomColor(): Int {
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)
        return Color.argb(0x88, r, g, b)
    }
}