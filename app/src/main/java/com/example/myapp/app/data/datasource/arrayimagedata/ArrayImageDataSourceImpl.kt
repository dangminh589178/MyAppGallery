package com.example.myapp.app.data.datasource.arrayimagedata

import com.example.myapp.app.data.local.model.ArrayImageResponse
import com.example.myapp.app.data.local.model.WallPaperResponse
import com.example.myapp.app.data.remote.ApiServices
import com.example.myapp.app.data.remote.apiCall
import retrofit2.http.Query
import javax.inject.Inject

/**
Crete by Minh at 15/02/2022
 **/
class ArrayImageDataSourceImpl @Inject constructor(private val apiServices: ApiServices) :
    ArrayImageDataSource {
    override suspend fun getArrayImage(): ArrayImageResponse = apiCall {
        apiServices.getData()
    }

    override suspend fun getArrayImageType(page: Int, perPage: Int): WallPaperResponse = apiCall {
        apiServices.wallPaperResponse(page, perPage)
    }
}
