package com.example.myapp.app.data.datasource.arrayimagedata

import com.example.myapp.app.data.local.model.ArrayImageResponse
import com.example.myapp.app.data.local.model.WallPaperResponse

/**
Crete by Minh at 15/02/2022
 **/
interface ArrayImageDataSource {
    suspend fun getArrayImage(): ArrayImageResponse

    suspend fun getArrayImageType(page:Int, perPage: Int): WallPaperResponse

}
