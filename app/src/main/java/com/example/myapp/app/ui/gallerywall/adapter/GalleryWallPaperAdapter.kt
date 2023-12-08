package com.example.myapp.app.ui.gallerywall.adapter

import android.inputmethodservice.Keyboard.Row
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.myapp.app.data.local.model.RowObject
import com.example.myapp.app.data.local.model.TypeArray
import com.example.myapp.app.extensions.ViewpagerExtension.addCarouselEffect
import com.example.myapp.app.extensions.setSafeOnClick
import com.example.myapp.app.ui.base.BaseListAdapter
import com.example.myapp.app.ui.gallerywall.model.CategoryViewType
import com.example.myapp.app.ui.gallerywall.model.GalleryCategoryItem
import com.example.myapp.databinding.ItemGalleryCategoryBinding
import com.example.myapp.databinding.ViewpagerCategoryItemBinding

/**
Crete by Minh at 2/03/2022
 **/
class GalleryWallPaperAdapter : BaseListAdapter<GalleryCategoryItem>() {

    internal var itemClickSeeMore: (listImage: ArrayList<RowObject>?) -> Unit = {}
    internal var itemClickImage: (dataImage: RowObject) -> Unit = {}

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder =

        when (viewType) {
            CategoryViewType.TITLE.ordinal -> {
                TitleViewHolder(
                    ItemGalleryCategoryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                ContentViewHolder(
                    ViewpagerCategoryItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }

    inner class TitleViewHolder(val binding: ItemGalleryCategoryBinding) :
        BaseItemViewHolder(binding.root) {

        override fun bind(data: GalleryCategoryItem) {
            val model = data as? GalleryCategoryItem.TitleItem
            binding.titleCategory.text = model?.title
            binding.tvSeeMore.setSafeOnClick {
                itemClickSeeMore.invoke(model?.listDetailImage)
            }
        }
    }

    inner class ContentViewHolder(val binding: ViewpagerCategoryItemBinding) :
        BaseItemViewHolder(binding.root) {
        private val carouseImageCategoryAdapter : CarouseImageCategoryAdapter by lazy{
            CarouseImageCategoryAdapter( clickItemImage = itemClickImage  )
        }

        init {
            binding.vpImageCategory.adapter = carouseImageCategoryAdapter
            binding.vpImageCategory.orientation = ORIENTATION_HORIZONTAL
            binding.vpImageCategory.addCarouselEffect()
        }

        override fun bind(data: GalleryCategoryItem) {
            val model = data as GalleryCategoryItem.ContentItem
            carouseImageCategoryAdapter.submitList(model.data)
        }
    }
}
