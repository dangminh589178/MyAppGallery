package com.example.myapp.app.utils

import android.content.Context
import android.util.TypedValue
import android.webkit.URLUtil
import com.example.myapp.BuildConfig

object Utils {

    fun convertImageUrl( url: String?): String {
        return when{
            url.isNullOrEmpty() -> ""
            URLUtil.isHttpUrl(url) || URLUtil.isHttpsUrl(url) -> url
            else -> BuildConfig.BASE_IMAGE + url
        }
    }

    fun dpToPixels(context: Context?, dpValue: Float): Float{
        context?.apply {
            val displayMetrics = this.resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, displayMetrics)
        }
        return 0f
    }
}
