package com.example.myapp.app.ui.gallerydetail

import android.util.Log
import com.example.myapp.app.data.local.model.RowObject
import com.example.myapp.app.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
Crete by Minh at 6/03/2022
 **/
@HiltViewModel
class GalleryWallPaperDetailViewModel @Inject constructor() : BaseViewModel() {

    var urlImage : MutableStateFlow<MutableList<RowObject>> = MutableStateFlow(mutableListOf())

    internal fun setData(rowObject: ArrayList<RowObject>) {
        urlImage.value = rowObject
        Log.d("setDataToStringsdf", rowObject.size.toString())
//        rowObject.forEach {
//            urlImage.value = it.row
//        }
    }
}
