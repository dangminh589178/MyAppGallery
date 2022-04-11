package com.example.myapp.app.ui.picture_detail

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapp.R
import com.example.myapp.app.data.local.model.RowObject
import com.example.myapp.app.extensions.setSafeOnClick
import com.example.myapp.app.ui.base.BaseFragment
import com.example.myapp.databinding.FragmentPictureDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
Crete by Minh at 9/03/2022
 **/
@AndroidEntryPoint
class PictureDetailFragment : BaseFragment() {

    companion object {
        private const val IMAGE = "URL_IMAGE"

        internal fun newInstance(image: RowObject) = PictureDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(IMAGE, image)
            }
        }
    }

    private var binding: FragmentPictureDetailBinding? = null
    private val viewModel by viewModels<PictureDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ndsjasjbilj", "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_picture_detail,
            container,
            false
        )
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            data = viewModel
        }
        initData()
        initEvent()
        Log.d("PictureDetailFragmentasdasdk", "onCreateView: ")
        return binding?.root
    }

    private fun initEvent() {
        binding?.apply {
            btnApply.setSafeOnClick {
                arguments?.getParcelable<RowObject>(IMAGE)?.also { row ->
                    showBottomSheet(WallPaperBottomSheetFragment.newInstance(row.row.url))
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            launch {
                viewModel.loadingState().collect {
                    handleShowLoading(it)
                }
            }

            launch {
                viewModel.imageObject.collect {
                    Log.d("asdsadasdaaaaa", it.title)
                }
            }
        }
    }

    private fun initData() {
        arguments?.getParcelable<RowObject>(IMAGE)?.apply {
            this.also { data ->
                viewModel.setData(data)

//                WallPaperBottomSheetFragment.newInstance(data.row.url)

                Log.d("datafrompicturedetail", data.row.url)
            }
        }
    }
}
