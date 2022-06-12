package com.example.myapp

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.myapp.app.extensions.addFragment
import com.example.myapp.app.extensions.replaceFragment
import com.example.myapp.app.ui.gallerywall.GalleryWallPaperFragment
import com.example.myapp.app.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivityLaunchJumphtp", "MainActivityLaunch")
        lifecycleScope.launchWhenCreated {
            Log.d("asdsadlaunchWhenCreated", "sadasd")
                replaceFragment(
                    GalleryWallPaperFragment(), isAddBackStack = true, isEnableAnim = true
                )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ssdawsadsadsad", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ssdawsadsadsad", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ssdawsadsadsad", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ssdawsadsadsad", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ssdawsadsadsad", "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ssdawsadsadsad", "onRestart: ")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("onBackPressedluanhehre", "onBackPressed: ")
    }
}
