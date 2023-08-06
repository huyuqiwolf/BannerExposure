package com.example.recyclerjank.mock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.recyclerjank.MockDataFactory
import com.example.recyclerjank.R
import com.example.recyclerjank.databinding.ActivityMockBinding
import com.example.recyclerjank.ui.entity.RemoteImage
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

private const val TAG = "MockActivity"
private const val START_STR = "<img src="
private const val END_STR = ">"
private const val LEN = START_STR.length + 1

class MockActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMockBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launch(IO) {
            val client = OkHttpClient.Builder()
                .build()
            loadIcons(client)
        }
    }

    private fun loadIcons(client: OkHttpClient) {
        val array = mutableListOf<RemoteImage>()
        var count = 0
        MockDataFactory.urlsSource.forEach { url ->
            val request = Request.Builder().get()
                .url(url).build()
            val response = client.newCall(request).execute()
            response.body?.string()?.let {
                val start = it.indexOf(START_STR)
                val end = it.indexOf(END_STR) - 1
                val str = it.substring(start + LEN, end)
                //Log.e(TAG, "onResponse: ${str}")
                array.add(RemoteImage(str))
            }

            count++
            Log.e(TAG, "url index ${MockDataFactory.urls.size} $count")
        }
//        Log.e(TAG, "${array.toString()}")

        File(getExternalFilesDir(null), "urls.json").writeText(
            Gson().toJson(array),
            Charset.defaultCharset()
        )
        count = 0
        val icons = mutableListOf<RemoteImage>()
        MockDataFactory.iconsSource.forEach { url ->
            val request = Request.Builder().get()
                .url(url).build()
            val response = client.newCall(request).execute()
            val str = response.body?.string()?.let {
                val start = it.indexOf(START_STR)
                val end = it.indexOf(END_STR)
                val str = it.substring(start + LEN, end)
                //Log.e(TAG, "onResponse: ${str}")
                icons.add(RemoteImage(str))
            }

            count++
            Log.e(TAG, "icon index ${MockDataFactory.urls.size} $count")
        }

        File(getExternalFilesDir(null), "icons.json").writeText(
            Gson().toJson(icons),
            Charset.defaultCharset()
        )
        Log.e(TAG, "loadIcons: =================")
    }
}