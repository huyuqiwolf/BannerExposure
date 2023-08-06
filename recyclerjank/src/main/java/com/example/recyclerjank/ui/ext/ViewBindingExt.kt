package com.example.recyclerjank.ui.ext

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMarginsRelative
import androidx.viewbinding.ViewBinding
import com.example.recyclerjank.ui.entity.LayoutType
import com.example.recyclerjank.ui.entity.getContentHeight


inline fun <T : View> T.applyHeight(block: (Context) -> Int): T {
    this.updateLayoutParams {
        height = block(this@applyHeight.context)
//        (this as? ViewGroup.MarginLayoutParams)?.let { params ->
//            params.updateMarginsRelative(top = 4, bottom = 4)
//        }
    }
    return this
}