package com.example.myapp.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.myapp.app.data.remote.ApiServices
import com.example.myapp.app.ui.paging_source.WallPaperPagingSource
import javax.inject.Inject
import javax.inject.Singleton

/**
Crete by Minh at 12/06/2022
 **/

@Singleton
class DataRepository @Inject constructor(private val apiServices: ApiServices) {

    fun getDataWallPaper() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { WallPaperPagingSource(apiServices) }
        ).flow
}
