package com.example.myapp.app.data.local.model

import com.google.gson.annotations.SerializedName

/**
Crete by Minh at 15/02/2022
 **/

data class ArrayImageResponse(
    @SerializedName("data")
    val data: ArrayList<TypeArray>
)
