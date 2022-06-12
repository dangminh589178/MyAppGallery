package com.example.myapp.app.ui.gallerywall

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapp.app.data.local.model.TypeArray
import com.example.myapp.app.data.repository.ArrayImageRepository
import com.example.myapp.app.extensions.bindLoading
import com.example.myapp.app.extensions.onError
import com.example.myapp.app.extensions.onSuccess
import com.example.myapp.app.ui.base.viewmodel.BaseViewModel
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

//    val typeArray = MutableStateFlow(TypeArray("0"))
    var listResponseImage: MutableStateFlow<MutableList<TypeArray>> = MutableStateFlow(mutableListOf())

    internal fun getDataResponse() {
        galleryRepo.getArrayImageResponse()
            .onSuccess {
                Log.d("arrayImageResponse", it.data.size.toString())
                listResponseImage.value = it.data
                Log.d("arrayImageResponseToString", listResponseImage.value.size.toString())

            }.onError()
            .bindLoading(this).launchIn(viewModelScope)
    }
}
