package com.example.myapp.app.util.bindingadapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapp.BuildConfig

/**
Crete by Minh at 15/02/2022
 **/

@BindingAdapter("loadImageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.toString().isNotEmpty()){
        Glide.with(context).load("${BuildConfig.BASE_IMAGE}$url")
            .into(this)
    }
}
