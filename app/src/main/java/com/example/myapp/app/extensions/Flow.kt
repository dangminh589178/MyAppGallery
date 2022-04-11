package com.example.myapp.app.extensions

import com.example.myapp.app.data.remote.exeption.BaseError
import com.example.myapp.app.ui.base.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.net.UnknownHostException


/**
Crete by Minh at 23/02/2022
 **/

sealed class FlowResult<out T> {
    data class Success<T>(val value: T) : FlowResult<T>()
    data class Error(val baseError: BaseError) : FlowResult<Nothing>()
}

inline fun <T> safeFlow(
    crossinline block: suspend () -> T
): Flow<FlowResult<T>> = flow {
    try {
        val repoResult = block()
        emit(FlowResult.Success(repoResult))
    } catch (e: BaseError) {
        e.printStackTrace()
    } catch (e: UnknownHostException) {
        println(e.printStackTrace())
    } catch (e: Exception) {
        println(e.printStackTrace())
    }
}

fun <T> Flow<FlowResult<T>>.onSuccess(action: suspend (T) -> Unit): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Success<T>) {
            action(result.value)
        }
        return@transform emit(result)
    }

fun <T> Flow<FlowResult<T>>.onError(
    action: suspend (BaseError) -> Unit = {}
): Flow<FlowResult<T>> = transform { result ->
    if (result is FlowResult.Error) {
        action(result.baseError)
    }
}

fun <H, X> Flow<H>.bindLoading(x: X): Flow<H> where X : BaseViewModel =

    this.onStart {
        x.handleLoading(true)
    }.onCompletion {
        x.handleLoading(false)
    }

fun <H, X> Flow<FlowResult<H>>.bindError(x: X): Flow<FlowResult<H>> where X : BaseViewModel =
    this.onError {
        x.handleError(it)
    }















