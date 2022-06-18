package com.example.myapp.app.data.local.model

import com.google.gson.annotations.SerializedName

/**
Crete by Minh at 12/06/2022
 **/
data class WallPaperResponse(
    @SerializedName("data")
    val data: ArrayList<TypeArray>
)