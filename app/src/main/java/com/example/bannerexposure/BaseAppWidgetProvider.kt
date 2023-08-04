package com.example.bannerexposure

import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log


open class BaseAppWidgetProvider : AppWidgetProvider() {
    companion object {
        private const val TAG = "BaseAppWidgetProvider"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "onReceive: ${intent?.action}")
        if (intent?.hasExtra("LIFECYCLE") == true) {
            when (intent.getStringExtra("LIFECYCLE")) {
                "onShow" -> {
                    onShow(context, intent)
                }

                "onHide" -> {
                    onHide(context, intent)
                }

                else -> {
                    super.onReceive(context, intent)
                }
            }
        } else {
            super.onReceive(context, intent)
        }
    }

    open fun onHide(context: Context?, intent: Intent) {

    }

    open fun onShow(context: Context?, intent: Intent) {

    }
}