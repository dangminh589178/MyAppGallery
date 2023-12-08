package com.example.myapp.app.ui.gallerydetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapp.R
import com.example.myapp.app.data.local.model.RowObject
import com.example.myapp.app.extensions.addFragment
import com.example.myapp.app.ui.base.BaseActivity
import com.example.myapp.app.ui.base.BaseFragment
import com.example.myapp.app.ui.gallerydetail.adapter.GalleryWallPaperDetailAdapter
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
    private val paperDetailAdapter by lazy {
        GalleryWallPaperDetailAdapter()
    }

    companion object {
        const val TYPE_ARRAY_DATA_DETAIL = "TYPE_ARRAY_DATA_DETAIL"

        internal fun newInstance(typeArray: ArrayList<RowObject>) = GalleryWallPaperDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(TYPE_ARRAY_DATA_DETAIL, typeArray)
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
        initData()
        initEvent()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }
    private fun initEvent() {
        paperDetailAdapter.apply {
            onClickGetImageUrl {
                (activity as? BaseActivity)?.addFragment(PictureDetailFragment.newInstance(it), true, null)
            }
        }
    }

    private fun initView() {
        binding?.rcvWallPaperDetail?.apply {
            adapter = paperDetailAdapter
        }

        lifecycleScope.launchWhenCreated {
            launch {
                viewModel.loadingState().collect {
                    handleStateLoading(it)
                }
            }

            launch {
                viewModel.urlImage.collect {
                    if (it.size > 0) {
                        Log.d("lasdndasdla", "initView: ")
                        paperDetailAdapter.submitList(it)
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

    private fun initData() {
        arguments?.apply {
            getParcelableArrayList<RowObject>(TYPE_ARRAY_DATA_DETAIL)?.let { dataDetail ->
                viewModel.setData(dataDetail)
            }
        }
    }
}
