package com.example.myapp.app.extensions

import android.os.SystemClock
import android.view.View

/**
Crete by Minh at 13/01/2022
 **/

fun View.setSafeOnClick(delay: Long = 1000, onSafeOnclick: () -> Unit) {
    var timeNow = 0L
    setOnClickListener {
        SystemClock.elapsedRealtime().run {
            if (this - timeNow < delay) {
                return@setOnClickListener
            }
            timeNow = this
            onSafeOnclick.invoke()
        }
    }
}
