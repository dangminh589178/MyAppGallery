package com.example.myapp.app.ui.base

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapp.app.extensions.addFragment
import com.example.myapp.app.extensions.replaceFragment
import com.example.myapp.app.ui.common.LoadingDiaLog
import com.example.myapp.databinding.ActivityMainBinding

/**
Crete by Minh at 2/01/2022
 ** For common and logic for MainActivity
 **/
abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private const val DELAY_MESSAGE = 7000L
    }

    private var navigationContainer: BaseFragment? = null
    private var noNavigationContainer: BaseFragment? = null
    private val loadingDialog by lazy { LoadingDiaLog() }
    private var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navigationContainer?.let {
            replaceFragment(it, false, isEnableAnim = false)
        }
        noNavigationContainer?.let {
            addFragment(it, false)
        }
    }

    internal fun addNoNavigationFragment(
        fragment: Fragment,
        isEnableAnim: Boolean = true,
        tagNameBackStack: String? = null
    ) {
        noNavigationContainer?.addInContainer(fragment, isEnableAnim, tagNameBackStack)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        super.dispatchTouchEvent(ev)
        ev?.let {
            if (ev.action == MotionEvent.ACTION_DOWN) {
                val viewTemp: View? = currentFocus
                if (viewTemp is EditText) {
                    val outRect = Rect()
                    viewTemp.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                        viewTemp.clearFocus()
                        hideKeyboard(viewTemp)
                    }
                }
            }
        }
        return true
    }

    private fun hideKeyboard(v: View?) {
        v?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    internal fun reCreateActivitySmooth() {
        startActivity(intent?.also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }

    fun showLoading() {
        loadingDialog.show(supportFragmentManager, loadingDialog.javaClass.name)
    }

    internal fun hideLoading() {
        loadingDialog.dismiss()
    }
}
