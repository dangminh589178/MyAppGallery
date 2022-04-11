package com.example.myapp.app.data.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
Crete by Minh at 6/03/2022
 **/

@Parcelize
open class IntialData(
    val typeDataDetail: TypeArray? = null
) : Parcelable
