package com.example.myapp.app.ui.gallerywall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapp.R
import com.example.myapp.app.data.local.model.RowObject
import com.example.myapp.app.extensions.GlideExtension
import com.example.myapp.app.ui.base.BaseListAdapter
import com.example.myapp.databinding.ViewpagerCategoryDetailBinding

class CarouseImageCategoryAdapter : BaseListAdapter<RowObject>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        return CarouselDetailImage(
            ViewpagerCategoryDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    inner class CarouselDetailImage(val binding: ViewpagerCategoryDetailBinding) :
        BaseItemViewHolder(binding.root) {

        override fun bind(data: RowObject) {
            binding.apply {
                    GlideExtension.withLoad(binding.root.context, data.row.url).into(imgCategory)
            }
        }
    }
}
