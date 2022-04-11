package com.example.myapp.app.ui.gallerydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapp.app.data.local.model.RowObject
import com.example.myapp.app.ui.base.BaseListAdapter
import com.example.myapp.databinding.ItemGalleryWallpaperDetailBinding

/**
Crete by Minh at 6/03/2022
 **/

class GalleryWallPaperDetailAdapter : BaseListAdapter<RowObject>() {

    var onClick: (data: RowObject) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        RowObjectViewHolder(
            ItemGalleryWallpaperDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class RowObjectViewHolder(private var binding: ItemGalleryWallpaperDetailBinding) :
        BaseItemViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick.invoke(getItem(adapterPosition))
            }
        }

        override fun bind(data: RowObject) {
            binding.data = data
        }
    }

    fun onClickGetImageUrl(data: (RowObject) -> Unit) {
        this.onClick = data
    }

}