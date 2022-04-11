package com.example.myapp.app.ui.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
Crete by Minh at 2/01/2022
 **/
abstract class BaseDialogFragment : DialogFragment() {
    companion object {
        private const val dimValue = 0.5f

        @Volatile
        private var isShowing = false
    }

    var onBackPressedCallback :() -> Unit ={}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireContext()){
            override fun onBackPressed() {
                onBackPressedCallback
            }
        }.apply {
            window?.run {
                requestFeature(Window.FEATURE_NO_TITLE)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setDimAmount(dimValue)
            }
            setCancelable(false) //dim behind window
            setCanceledOnTouchOutside(false)
            setContentDialog(this)
            initListener(this)
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isShowing  && !isAdded ){
            isShowing = true
            super.show(manager, tag)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (isShowing){
            isShowing = false
        }
        super.onDismiss(dialog)
    }

    abstract fun setContentDialog(dialog: Dialog)

    abstract fun initListener(dialog: Dialog)

}