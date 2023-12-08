package com.example.myapp.app.ui.base.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapp.app.data.remote.exeption.BaseError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
Crete by Minh at 26/02/2022
 **/
abstract class BaseViewModel : ViewModel() {
    private var loadingState = MutableStateFlow(false)
    private var errorState = MutableStateFlow<BaseError?>(null)

    internal fun loadingState(): StateFlow<Boolean> = loadingState
    internal fun errorState(): StateFlow<BaseError?> = errorState

    internal fun handleLoading(isLoading: Boolean) {
        loadingState.value = isLoading
    }

    fun handleError(error : BaseError?) {
        errorState.value = error
    }

}
