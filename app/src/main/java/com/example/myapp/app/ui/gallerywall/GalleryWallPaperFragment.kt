package com.example.myapp.app.ui.gallerywall

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.app.extensions.addFragment
import com.example.myapp.app.ui.base.BaseActivity
import com.example.myapp.app.ui.base.BaseFragment
import com.example.myapp.app.ui.gallerydetail.GalleryWallPaperDetailFragment
import com.example.myapp.app.ui.gallerywall.adapter.GalleryWallPaperAdapter
import com.example.myapp.databinding.FragmentGalleryWallpaperBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
Crete by Minh at 26/02/2022
 **/

@AndroidEntryPoint
class GalleryWallPaperFragment : BaseFragment() {
    private val viewModel by viewModels<GalleryWallPaperViewModel>()
    private var binding: FragmentGalleryWallpaperBinding? = null
    private val adapter: GalleryWallPaperAdapter by lazy {
        GalleryWallPaperAdapter()
    }
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("kasdjkssssds", "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_gallery_wallpaper,
            container,
            false
        )
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner


        }
        Log.d("kasdjkssssds", "onCreateView: ")
        initData()
        initLaunch()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("kasdjkssssds", "onViewCreated: ")

        initView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("kasdjkssssds", "onAttach: ")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("kasdjkssssds", "onDetach: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("kasdjkssssds", "onResume: ")
    }

    override fun onPause() {
        super.onPause()

        Log.d("kasdjkssssds", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("kasdjkssssds", "onStop: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("kasdjkssssds", "onSaveInstanceState: ")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("kasdjkssssds", "onViewStateRestored: ")
    }
    override fun onStart() {
        super.onStart()

        Log.d("kasdjkssssds", "onStart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("kasdjkssssds", "onDestroy: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initLaunch() {
        lifecycleScope.launchWhenCreated {
            launch {
                viewModel.loadingState().collect {
                    handleStateLoading(it)
                }
            }
            launch {
                viewModel.listResponseImage.collect {
                    if (it.size > 0) {
                        adapter.submitList(viewModel.listResponseImage.value)
                    } else {
                        Log.d("zỏtosngdsdf", "adsad: ")
                    }
                }
            }
        }
    }

    private fun handleStateLoading(stateLoading: Boolean) {
        if (!stateLoading){
            binding?.loadingView?.visibility = View.GONE
        }
    }

    private fun initView() {
        val layoutManager = GridLayoutManager(context, 1)
        binding?.rcvGalleyWallPaper?.let {
            it.adapter = adapter
            it.layoutManager = layoutManager

        }
    }

    private fun initData() {
        adapter.apply {
            itemClick = {
                    (activity as? BaseActivity)?.addFragment(GalleryWallPaperDetailFragment.newInstance(it), true, null)

            }
        }
        viewModel.getDataResponse()
    }


}
