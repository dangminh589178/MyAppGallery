package com.example.myapp.app.ui.paging_source

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapp.app.data.local.model.TypeArray
import com.example.myapp.app.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
Crete by Minh at 13/06/2022
 **/
@HiltViewModel
class WallPaperViewModel @Inject constructor(
    private val repository: DataRepository,
    state: SavedStateHandle
) : ViewModel() {

    fun getDataFromApi(): Flow<PagingData<TypeArray>> =
        repository.getDataWallPaper().cachedIn(viewModelScope)
}
