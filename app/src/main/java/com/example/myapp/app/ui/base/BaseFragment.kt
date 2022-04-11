package com.example.myapp.app.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController

/**
Crete by Minh at 2/01/2022
 ** Common UI
 **/
abstract class BaseFragment : com.example.myapp.app.ui.base.FragmentController() {
    internal fun addNoNavigationFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        (activity as? BaseActivity)?.addNoNavigationFragment(
            fragment,
            isEnableAnim,
            tagNameBackStack
        )
    }

    internal fun showLoading() {
        (activity as? BaseActivity)?.showLoading()

    }

    internal fun handleShowLoading(isStateShow: Boolean) {
        if (isStateShow) {
            showLoading()
        } else {
            hideLoading()
        }

    }

    private fun hideLoading() {
        (activity as? BaseActivity)?.hideLoading()
    }

    internal fun showBottomSheet(bottomSheet: BaseBottomSheetDialog) {
        bottomSheet.show(parentFragmentManager, bottomSheet.javaClass.canonicalName)
    }

}
