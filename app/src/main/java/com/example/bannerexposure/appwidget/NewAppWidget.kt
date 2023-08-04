package com.example.bannerexposure.appwidget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.example.bannerexposure.BaseAppWidgetProvider
import com.example.bannerexposure.R

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : BaseAppWidgetProvider() {
    companion object{
        private const val TAG = "NewAppWidget"
    }
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        Log.e(TAG, "onUpdate: ", )
    }

    override fun onShow(context: Context?, intent: Intent) {
        super.onShow(context, intent)
        Log.e(TAG, "onShow: ", )
    }

    override fun onHide(context: Context?, intent: Intent) {
        super.onHide(context, intent)
        Log.e(TAG, "onHide: ", )
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}