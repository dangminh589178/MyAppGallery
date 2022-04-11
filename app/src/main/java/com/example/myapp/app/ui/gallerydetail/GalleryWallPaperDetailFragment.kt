package com.example.myapp.app.ui.gallerydetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.app.data.local.model.TypeArray
import com.example.myapp.app.extensions.addFragment
import com.example.myapp.app.ui.base.BaseActivity
import com.example.myapp.app.ui.base.BaseFragment
import com.example.myapp.app.ui.picture_detail.PictureDetailFragment
import com.example.myapp.databinding.FragmentGalleryWallpaperDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
Crete by Minh at 5/03/2022
 **/
@AndroidEntryPoint
class GalleryWallPaperDetailFragment : BaseFragment() {
    private val viewModel by viewModels<GalleryWallPaperDetailViewModel>()
    private var binding: FragmentGalleryWallpaperDetailBinding? = null
    private val adapter by lazy {
        GalleryWallPaperDetailAdapter()
    }

    companion object {
        const val TYPE_ARRAY_DATA_DETAIL = "TYPE_ARRAY_DATA_DETAIL"

        internal fun newInstance(typeArray: TypeArray) = GalleryWallPaperDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TYPE_ARRAY_DATA_DETAIL, typeArray)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_gallery_wallpaper_detail,
            container,
            false
        )
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        initView()
        initData()
        initEvent()
        return binding?.root
    }

    private fun initEvent() {
        adapter.apply {
            onClickGetImageUrl {
                (activity as? BaseActivity)?.addFragment(PictureDetailFragment.newInstance(it), true, null)
            }
        }
    }

    private fun initView() {
        val layoutManager = GridLayoutManager(context, 2)
        binding?.rcvWallPaperDetail?.also {
            it.adapter = adapter
            it.layoutManager = layoutManager
            it.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        lifecycleScope.launchWhenCreated {
            launch {
                viewModel.loadingState().collect {
                    handleShowLoading(it)
                }
            }

            launch {
                viewModel.urlImage.collect {
                    if (it.size > 0) {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun initData() {
        arguments?.apply {
            getParcelable<TypeArray>(TYPE_ARRAY_DATA_DETAIL).apply {
                this?.also {
                    viewModel.setData(it.data_loai)
                }
            }
        }
    }
}
