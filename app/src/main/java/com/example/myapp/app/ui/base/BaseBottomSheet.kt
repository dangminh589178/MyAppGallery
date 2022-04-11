package com.example.myapp.app.ui.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.myapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
Crete by Minh at 11/03/2022
 **/

abstract class BaseBottomSheetDialog : BottomSheetDialogFragment() {

    companion object {
        private const val ZERO = 0
        private var isShowing = false
    }
    internal var heightOfDialog: Int = ZERO


    internal var heightSize: Int = 0

    override fun onStart() {
        super.onStart()
        fixHeightForDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.SheetDialog )
    }
    
    override fun show(manager: FragmentManager, tag: String?) {
        if (!isShowing && !isAdded) {
            isShowing = true
            super.show(manager, tag)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (isShowing) {
            isShowing = false
            super.onDismiss(dialog)
        }
    }

    override fun dismiss() {
        if (isShowing && isAdded) {
            isShowing = false
            super.dismiss()
        }
    }

    private fun fixHeightForDialog() {
        if (heightOfDialog != ZERO) {
            dialog?.also {
                val bottomSheet = it.findViewById<View>(R.id.design_bottom_sheet)
                bottomSheet.layoutParams.height = heightOfDialog
                val behavior = BottomSheetBehavior.from(bottomSheet)
                behavior.peekHeight = heightOfDialog
                view?.requestLayout()
            }
        }
    }

}
