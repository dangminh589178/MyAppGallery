package com.example.myapp.app.ui.picture_detail

import android.util.Log
import com.example.myapp.app.data.local.model.ImageInfo
import com.example.myapp.app.data.local.model.RowObject
import com.example.myapp.app.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
Crete by Minh at 11/03/2022
 **/

@HiltViewModel
class PictureDetailViewModel @Inject constructor() : BaseViewModel() {
    var imageObject = MutableStateFlow(ImageInfo())

    internal fun setData(data: RowObject) {
        imageObject.value = data.row
        Log.d("typetostring", imageObject.value.url)
    }
}
