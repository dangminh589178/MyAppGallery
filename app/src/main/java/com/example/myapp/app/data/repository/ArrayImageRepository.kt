package com.example.myapp.app.data.repository

import com.example.myapp.app.data.datasource.arrayimagedata.ArrayImageDataSourceImpl
import com.example.myapp.app.extensions.safeFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
Crete by Minh at 23/02/2022
 **/

class ArrayImageRepository @Inject constructor(private val arrayImage: ArrayImageDataSourceImpl) {
    internal  fun getArrayImageResponse() = safeFlow {
        arrayImage.getArrayImage()
    }

    internal fun getArrayImageTypeResponse(page: Int, perPage: Int) = safeFlow {
        arrayImage.getArrayImageType(page, perPage)
    }
}
