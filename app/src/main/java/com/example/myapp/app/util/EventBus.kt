package com.example.myapp.app.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
Crete by Minh at 13/01/2022
 **/
object EventBus {
    private val events = MutableSharedFlow<Any>()
    val subject = events.asSharedFlow()
    suspend fun invokeEvent(event : Any) = events.emit(event)
}
