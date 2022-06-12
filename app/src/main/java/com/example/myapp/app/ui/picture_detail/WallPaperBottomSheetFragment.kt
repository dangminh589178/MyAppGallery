package com.example.myapp.app.ui.picture_detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.myapp.R
import com.example.myapp.app.data.AppConstant
import com.example.myapp.app.extensions.setSafeOnClick
import com.example.myapp.app.ui.base.BaseBottomSheetDialog
import com.example.myapp.databinding.FragmentWallPaperBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch
import com.squareup.picasso.Picasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.with
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.myapp.BuildConfig
import com.example.myapp.BuildConfig.BASE_IMAGE
import android.app.WallpaperManager
import android.os.Build
import java.io.IOException


/**
Crete by Minh at 11/03/2022
 **/
class WallPaperBottomSheetFragment : BaseBottomSheetDialog() {
    companion object {
        const val URL_IMAGE = "URL_IMAGE"

        internal fun newInstance(url: String) = WallPaperBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putString(URL_IMAGE, url)
            }
        }
    }

    private var binding: FragmentWallPaperBottomSheetBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_wall_paper_bottom_sheet,
            container,
            false
        )

        return binding?.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            activity?.window?.decorView?.post {
                dialog?.dismiss()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("asmdnlasdjasdsad", "onAttach: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isShow", true)
    }

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }


    private fun initData() {
        binding?.apply {
            tvLockScreen.setSafeOnClick {
                Toast.makeText(context, "sss", Toast.LENGTH_SHORT).show()
            }

            tvHomeScreen.setSafeOnClick {
                handleOnclick()
            }

            btnCancel.setSafeOnClick {
                dismiss()
            }
        }
    }

    private fun handleOnclick() {
        arguments?.getString(URL_IMAGE)?.also { urlImage ->
            with(requireContext()).asBitmap()
                .load("$BASE_IMAGE$urlImage")
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val wallpaperManager = WallpaperManager.getInstance(context)
                        try {
                            wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_SYSTEM )
                            Toast.makeText(context, "Set succeed wallpaper", Toast.LENGTH_SHORT)
                                .show()




                        } catch (ex: IOException) {
                            ex.printStackTrace()
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })

        }
    }
}
