package com.example.myapp.app.ui.paging_source

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.BuildConfig
import com.example.myapp.app.data.local.model.TypeArray
import com.example.myapp.databinding.ItemGallaryPagingBinding

/**
Crete by Minh at 13/06/2022
 **/
class WallPaperAdapter :
    PagingDataAdapter<TypeArray, WallPaperAdapter.WallPaperViewHolder>(WALLPAPER_DIFF) {
    internal var clickData: (item: TypeArray?) -> Unit = {}

    inner class WallPaperViewHolder(private val binding: ItemGallaryPagingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                clickData.invoke(getItem(bindingAdapterPosition))
            }
        }

        @SuppressLint("CheckResult")
        fun bind(itemWallPaper: TypeArray) {
            binding.apply {
                tvTitlePaging.text = itemWallPaper.type
                Glide.with(itemView)
                    .load("${BuildConfig.BASE_IMAGE}${itemWallPaper.data_loai[0].row.url}")
                    .into(imvPicturePaging)
            }
        }
    }

    override fun onBindViewHolder(holder: WallPaperAdapter.WallPaperViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WallPaperViewHolder = WallPaperViewHolder(
        ItemGallaryPagingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    companion object {
        val WALLPAPER_DIFF = object : DiffUtil.ItemCallback<TypeArray>() {
            override fun areItemsTheSame(oldItem: TypeArray, newItem: TypeArray): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: TypeArray, newItem: TypeArray): Boolean =
                oldItem == newItem
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}
