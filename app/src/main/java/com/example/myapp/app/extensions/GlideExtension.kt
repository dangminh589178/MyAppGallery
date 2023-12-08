package com.example.myapp.app.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.myapp.app.utils.Utils

object GlideExtension {
    fun withLoad(context: Context, url: String?): RequestBuilder<Drawable> {
        val formatUrl = Utils.convertImageUrl(url)
         return Glide.with(context).load(formatUrl)
    }
}
