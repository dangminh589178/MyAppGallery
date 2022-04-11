package com.example.myapp.app.extensions

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapp.R

/**
Crete by Minh at 2/01/2022
 **/

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    isEnableAnim: Boolean = true,
    tagNameBackStack: String? = null
) {
    supportFragmentManager.beginTransaction().apply {
        if (isEnableAnim) {
            setCustomAnimations(
                R.anim.anim_slide_in_from_right,
                R.anim.anim_slide_in_from_right,
                R.anim.anim_slide_in_from_right,
                R.anim.anim_slide_in_from_right,
            )
        }
        if (fragment.isAdded && fragment.tag == tagNameBackStack) {
            return;
        } else {
            add(R.id.fragmentContainer, fragment, fragment.javaClass.name)
            addToBackStack(tagNameBackStack)
            commit()
        }
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    isAddBackStack: Boolean,
    isEnableAnim: Boolean = true,
    tagNameBackStack: String? = null
) {
    supportFragmentManager.beginTransaction().apply {
        if (isEnableAnim) {
            setCustomAnimations(
                R.anim.anim_slide_in_from_right,
                R.anim.anim_slide_in_from_right,
                R.anim.anim_slide_in_from_right,
                R.anim.anim_slide_in_from_right,
            )
        }
        replace(R.id.fragmentContainer, fragment, fragment.javaClass.simpleName)
        if (isAddBackStack) {
            addToBackStack(tagNameBackStack)
        }
        commit()
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("WrongConstant")
internal fun AppCompatActivity.isNetworkConnected(): Boolean {
    val cm =
        getSystemService(AppCompatActivity.CONNECTIVITY_DIAGNOSTICS_SERVICE) as ConnectivityManager
    return cm.activeNetwork != null
}
