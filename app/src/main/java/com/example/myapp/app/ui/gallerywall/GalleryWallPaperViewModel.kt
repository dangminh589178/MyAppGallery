package com.example.myapp.app.ui.gallerywall

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapp.app.data.local.model.RowObject
import com.example.myapp.app.data.local.model.TypeArray
import com.example.myapp.app.data.repository.ArrayImageRepository
import com.example.myapp.app.extensions.bindLoading
import com.example.myapp.app.extensions.onError
import com.example.myapp.app.extensions.onSuccess
import com.example.myapp.app.ui.base.viewmodel.BaseViewModel
import com.example.myapp.app.ui.gallerywall.model.GalleryCategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

/**
Crete by Minh at 26/02/2022
 **/

@HiltViewModel
class GalleryWallPaperViewModel @Inject constructor(val galleryRepo: ArrayImageRepository) :
    BaseViewModel() {

    companion object {
        const val ELEMENT_VISIBLE = 10
        const val ELEMENT_PICTURE = 5
    }

    var listResponseImage: MutableStateFlow<MutableList<GalleryCategoryItem>> =
        MutableStateFlow(mutableListOf())

    internal fun getDataResponse() {
        galleryRepo.getArrayImageResponse()
            .onSuccess {
                listResponseImage.value = convertToCategoryItemView(it.data)
            }.onError()
            .bindLoading(this).launchIn(viewModelScope)
    }

    private fun convertToCategoryItemView(data: ArrayList<TypeArray>): MutableList<GalleryCategoryItem> {
        val listItem = mutableListOf<GalleryCategoryItem>()

        data.take(ELEMENT_VISIBLE).forEach { typeArray ->
            listItem.add(GalleryCategoryItem.TitleItem(title = typeArray.type))
            listItem.add(GalleryCategoryItem.ContentItem(data = typeArray.data_loai.take(ELEMENT_PICTURE) as ArrayList<RowObject>))
        }
        return listItem
    }
}
