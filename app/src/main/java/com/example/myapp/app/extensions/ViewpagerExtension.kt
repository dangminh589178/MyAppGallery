package com.example.myapp.app.extensions

import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.myapp.R
import kotlin.math.abs

object ViewpagerExtension {

    private const val ONE = 1
    private const val ALPHA = 0.30f

    fun ViewPager2.addCarouselEffect() {
        clipToPadding = false
        clipChildren = false
        offscreenPageLimit = 1

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) //20
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)         //30
        setPageTransformer { page, position ->
            val offset = position * -(2 * offsetPx + pageMarginPx)
            page.translationX = offset
            page.scaleY = 1- (0.30f * abs(position))

        }
    }
}
