package com.example.myapp.app.ui.paging_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapp.app.data.local.model.TypeArray
import com.example.myapp.app.data.remote.ApiServices
import retrofit2.HttpException
import java.io.IOException

/**
Crete by Minh at 12/06/2022
 **/
private const val STARTING_INDEX_PAGE = 1

class WallPaperPagingSource(
    private val apiSerVices: ApiServices
) : PagingSource<Int, TypeArray>() {
    override fun getRefreshKey(state: PagingState<Int, TypeArray>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            return null
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TypeArray> {
        val position = params.key ?: STARTING_INDEX_PAGE
        Log.d("positionStringMyApp", position.toString())
        return try {
            val response = apiSerVices.wallPaperResponse(position, params.loadSize)
//            Log.d("responsetostring", response.result.size.toString())
//            val wallPaper = response
            LoadResult.Page(
                data = response.body()?.data ?: mutableListOf(),
                prevKey = if (position == STARTING_INDEX_PAGE) null else position - 1,
                nextKey = if (response.body()?.data?.isEmpty() == true) null else position + 1
            )
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }
}