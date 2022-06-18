package com.example.myapp.app.ui.paging_source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapp.R
import com.example.myapp.app.ui.base.BaseFragment
import com.example.myapp.databinding.FragmentPaperPagingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
Crete by Minh at 15/06/2022
 **/

@AndroidEntryPoint
class WallPaperPagingFragment : BaseFragment() {
    private var binding: FragmentPaperPagingBinding? = null
    private val viewModel: WallPaperViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WallPaperAdapter()
        binding?.apply {
            rcvGalleyWallPaperPaging.setHasFixedSize(true)
            rcvGalleyWallPaperPaging.adapter = adapter
        }
        lifecycleScope.launchWhenCreated {
            launch {
                viewModel.getDataFromApi().collect {
                    adapter.submitData(it)
                }
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
            R.layout.fragment_paper_paging, container, false
        )
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
