package com.example.myapp.app.data.remote

import com.example.myapp.app.data.local.model.ArrayImageResponse
import com.example.myapp.app.data.local.model.WallPaperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
Crete by Minh at 15/02/2022
 **/
interface ApiServices {
    @GET("dulieujson/data.json")
    suspend fun getData(): Response<ArrayImageResponse>

    @GET("dulieujson/data.json")
    suspend fun wallPaperResponse(
        @Query("page") page : Int,
        @Query("per_page") perPage : Int
    ): Response<WallPaperResponse>
}
