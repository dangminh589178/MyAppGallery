package com.example.myapp.app.extensions

import android.util.Log
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
                R.anim.anim_slide_out_to_left,
                R.anim.anim_slide_enter_from_left,
                R.anim.anim_slide_out_to_right,
            )
        }
        if (fragment.isAdded && fragment.tag == tagNameBackStack) {
            return;
        } else {
            add(R.id.fragmentContainer, fragment, fragment.javaClass.name)
            Log.d("fragmentjavaClasssimpleName", fragment.javaClass.simpleName)
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
        Log.d("isEnableAnimtostrngfsasda", isEnableAnim.toString())
        if (isEnableAnim) {
            setCustomAnimations(
                R.anim.anim_slide_in_from_right,
                R.anim.anim_slide_out_to_left,
                R.anim.anim_slide_enter_from_left,
                R.anim.anim_slide_out_to_right,
            )
        }
        if (fragment.isAdded && fragment.tag == tagNameBackStack) {
            return;
        } else{
            replace(R.id.fragmentContainer, fragment, fragment.javaClass.simpleName)
            commit()
        }
    }
}
