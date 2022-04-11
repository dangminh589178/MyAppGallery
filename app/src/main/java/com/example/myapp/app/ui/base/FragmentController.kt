package com.example.myapp.app.ui.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.myapp.R
import com.example.myapp.app.data.AppConstant

/**
Crete by Minh at 2/01/2022
 **/

//Provides integration points with a FragmentManager for a fragment host.
//It is the responsibility of the host to take care of the Fragment's lifecycle.
// The methods provided by FragmentController are for that purpose.

abstract class FragmentController : Fragment() {
    companion object {
        private const val LEVEL_RATIO = 2
    }

    internal var level: Int = 0
        private set // the setter is private and has the default implementation

    internal fun setLevel(level: Int) {
        this.level = level
    }


    //backStackName : back to fragment destination
    open fun handleBackPress(tagNameBackStack: String? = null) {
        when (level) {
            AppConstant.LEVEL_TOP, AppConstant.LEVEL_CONTAINER -> return
            AppConstant.LEVEL_TAB -> {
                if (parentFragmentManager.backStackEntryCount > 0) {
                    popBackStackName(tagNameBackStack)
                } else {
//                    exit app
                    activity?.finish()
                }
            }
            else -> {
                if (level % LEVEL_RATIO == 0) {
                    //have childFragment in viewpager
                    parentFragment?.childFragmentManager?.also {
                        if (it.backStackEntryCount > 0) {
                            // pop in child viewpager
                            popBackStackName(tagNameBackStack)
                        } else {
                            // child in viewpager size == 0
                            // pop in parent
                            (parentFragment as? FragmentController)?.handleBackPress()
                        }
                    }
                } else {
                    //container
                    (parentFragment as? FragmentController)?.handleBackPress()
                }
            }
        }
    }

    internal fun replaceInContainer(
        fragment: Fragment, isAddBackStack: Boolean,
        isEnableAnim: Boolean = true, tagNameBackstack: String? = null
    ) {
        (fragment as? BaseFragment)?.setLevel(level + 1)
        childFragmentManager.beginTransaction().apply {
            if (isEnableAnim) {
                setCustomAnimations(
                    R.anim.anim_slide_in_from_right,
                    R.anim.anim_slide_in_from_right,
                    R.anim.anim_slide_in_from_right,
                    R.anim.anim_slide_in_from_right,
                )
            }
            replace(R.id.fragmentContainer, fragment, fragment.javaClass.name)
            if (isAddBackStack) {
                addToBackStack(tagNameBackstack)
            }
            commit()
        }
    }

    internal fun addInContainer(
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        childFragmentManager.beginTransaction().apply {
            if (isEnableAnim) {
                setCustomAnimations(
                    R.anim.anim_slide_in_from_right,
                    R.anim.anim_slide_in_from_right,
                    R.anim.anim_slide_in_from_right,
                    R.anim.anim_slide_in_from_right,
                )
            }
            add(R.id.fragmentContainer, fragment, fragment.javaClass.name)
            addToBackStack(tagNameBackStack)
            commit()
        }
    }

    internal fun replaceFragment(
        fragment: Fragment,
        isAddBackStack: Boolean,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        val range = level - AppConstant.LEVEL_CONTAINER
        when (level) {
            //not use fragment manager of activity
            AppConstant.LEVEL_TOP -> return
            AppConstant.LEVEL_CONTAINER -> {
                //add to main tab follow
                replaceInContainer(
                    fragment,
                    isAddBackStack,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            else -> {
                // get fragment manager of fragment when level = 1(Main top container)
                var parentFm: Fragment? = this
                for (index in 1..range) {
                    parentFm = parentFm?.parentFragment
                }
                (parentFm as? FragmentController)?.replaceInContainer(
                    fragment,
                    isAddBackStack,
                    isEnableAnim,
                    tagNameBackStack
                )
            }

        }
    }

    internal fun addFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        val range = level - AppConstant.LEVEL_CONTAINER
        when (level) {
            //do not use fragment manager of activity
            AppConstant.LEVEL_TOP -> return
            AppConstant.LEVEL_CONTAINER -> {
                //add in main tab follow
                addInContainer(
                    fragment,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            else -> {
                // get fragment manager of fragment when level = 1(Main top container)
                var parentFm: Fragment? = this
                for (index in 1..range) {
                    parentFm = parentFm?.parentFragment
                }
                (parentFm as? FragmentController)?.addInContainer(
                    fragment, isEnableAnim, tagNameBackStack
                )
            }
        }
    }

    internal fun addInChildFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        when {
            // do not use fragment manager of activity
            level == AppConstant.LEVEL_TOP -> return
            level == AppConstant.LEVEL_CONTAINER || level % LEVEL_RATIO != 0 -> {
                // in this come.android.myapplication.base: container level is a odd number
                addInContainer(
                    fragment,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            level == AppConstant.LEVEL_TAB || level % LEVEL_RATIO == 0 -> {
                //child in viewpager
                (parentFragment as? FragmentController)?.addInContainer(
                    fragment,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            else -> return
        }

    }

    private fun popBackStackName(stackName: String?) {
        if (stackName.isNullOrBlank()) {
            parentFragmentManager.popBackStack()
        } else {
            parentFragmentManager.popBackStack(stackName, POP_BACK_STACK_INCLUSIVE)
        }
    }

    internal fun replaceInChildFragment(
        fragment: Fragment, isAddBackStack: Boolean,
        isEnableAnim: Boolean = true, tagNameBackStack: String? = null
    ) {
        when {
            // do not use fragment manager of activity
            level == AppConstant.LEVEL_TOP -> return
            level == AppConstant.LEVEL_CONTAINER || level % LEVEL_RATIO != 0 -> {
                // in this come.android.myapplication.base: container level is a odd number
                replaceInContainer(
                    fragment,
                    isEnableAnim,
                    isAddBackStack,
                    tagNameBackStack
                )
            }
            level == AppConstant.LEVEL_TAB || level % LEVEL_RATIO == 0 -> {
                //child in viewpager
                (parentFragment as? BaseFragment)?.replaceInContainer(
                    fragment,
                    isAddBackStack,
                    isEnableAnim,
                    tagNameBackStack
                )
            }
            else -> return
        }
    }
}
