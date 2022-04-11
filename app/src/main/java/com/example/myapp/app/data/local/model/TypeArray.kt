package com.example.myapp.app.data.local.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
Crete by Minh at 27/02/2022
 **/
@Parcelize
data class TypeArray(
    @SerializedName("type")
    val type: String,
    @SerializedName("data_loai")
    val data_loai: ArrayList<RowObject>
) : Parcelable
