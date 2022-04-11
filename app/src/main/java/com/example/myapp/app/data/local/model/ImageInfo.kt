package com.example.myapp.app.data.local.model

import android.os.Parcelable
import com.example.myapp.app.data.AppConstant
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
Crete by Minh at 27/02/2022
 **/

@Parcelize
data class ImageInfo(
    @SerializedName("id")
    val id: String = AppConstant.EMPTY,
    @SerializedName("type")
    val type: String = AppConstant.EMPTY,
    @SerializedName("title")
    val title: String = AppConstant.EMPTY,
    @SerializedName("url")
    val url: String = AppConstant.EMPTY

) : Parcelable
