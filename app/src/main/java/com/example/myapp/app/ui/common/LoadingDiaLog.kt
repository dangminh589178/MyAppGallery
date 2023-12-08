package com.example.myapp.app.ui.common

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.myapp.app.ui.base.BaseDialogFragment
import kotlin.math.log

/**
Crete by Minh at 2/01/2022
 **/
 class LoadingDiaLog : BaseDialogFragment() {

    companion object{
        private const val dimValue = 0.5f

        @Volatile
        private var isShowing = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  object : Dialog(requireContext()){
            override fun onBackPressed() {
                onBackPressedCallback()
            }
        }.apply {
            window?.run {
                requestFeature(Window.FEATURE_NO_TITLE)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setDimAmount(dimValue)
            }
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            setContentDialog(this)
            initListener(this)
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            if (!isShowing && !isAdded) {
                isShowing = true
                super.show(manager, tag)
            }
        } catch (
            e: IllegalStateException
        ){
            Log.d("showError", "show error + ${e}: ")
        }
    }
    override fun dismiss() {
        try {
            if (isShowing && isAdded) {
                isShowing = false
                super.dismiss()
            }
        } catch (e: IllegalStateException) {
            Log.d("showError", "show error + ${e}: ")
        }
    }

    override fun setContentDialog(dialog: Dialog) = Unit

    override fun initListener(dialog: Dialog) = Unit
}
