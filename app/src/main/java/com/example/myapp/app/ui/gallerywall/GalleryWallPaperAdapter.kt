package com.example.myapp.app.ui.gallerywall

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapp.app.data.local.model.TypeArray
import com.example.myapp.app.ui.base.BaseListAdapter
import com.example.myapp.databinding.ItemGalleryBinding

/**
Crete by Minh at 2/03/2022
 **/
class GalleryWallPaperAdapter : BaseListAdapter<TypeArray>() {

    internal var itemClick: (typeArray: TypeArray) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =
        GalleryViewHolder(
            ItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    fun setonClickListener(onClick: (TypeArray) -> Unit) {
        this.itemClick = onClick
    }

    inner class GalleryViewHolder(private var binding: ItemGalleryBinding) :
        BaseItemViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClick.invoke(getItem(adapterPosition))
            }
        }

        override fun bind(data: TypeArray) {
            Log.d("lopndhasj", data.toString())
            binding.data = data
        }
    }
}
