package com.example.myapp.app.data.remote

import com.example.myapp.app.data.local.model.ArrayImageResponse
import retrofit2.Response
import retrofit2.http.GET

/**
Crete by Minh at 15/02/2022
 **/
interface ApiServices {
    @GET("dulieujson/data.json")
    suspend fun getData(): Response<ArrayImageResponse>
}
