package com.example.myapp.app.ui.gallerywall.model

import com.example.myapp.app.data.local.model.ImageInfo
import com.example.myapp.app.data.local.model.RowObject

sealed class GalleryCategoryItem(val type: CategoryViewType) {

    class TitleItem(val title: String = "") : GalleryCategoryItem(CategoryViewType.TITLE)
    class ContentItem(val data: ArrayList<RowObject>) : GalleryCategoryItem(CategoryViewType.CONTENT)
}

enum class CategoryViewType {
    TITLE,
    CONTENT,
}
