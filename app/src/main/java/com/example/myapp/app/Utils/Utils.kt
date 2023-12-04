package com.example.myapp.app.Utils

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
}
