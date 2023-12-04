package com.example.myapp.app.extensions

import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.myapp.R
import kotlin.math.abs

object ViewpagerExtension {

    fun ViewPager2.addCarouselEffect() {
        clipToPadding = false
        clipChildren = false
        offscreenPageLimit = 1

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) //20
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)         //30
        setPageTransformer { page, position ->
            val offset = position * -(2 * offsetPx + pageMarginPx)
            page.translationX = offset
            Log.d("addCaraouselEffect", " position: $position addCarouselEffect: ${1- (0.25f * abs(position))} ")
            page.scaleY = 1- (0.30f * abs(position))

        }
    }
}
