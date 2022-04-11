package com.example.myapp.app.data.datasource.arrayimagedata

import com.example.myapp.app.data.local.model.ArrayImageResponse

/**
Crete by Minh at 15/02/2022
 **/
interface ArrayImageDataSource {
    suspend fun getArrayImage(): ArrayImageResponse

}
