package com.example.myapp.app.data.remote

import retrofit2.Response

/**
Crete by Minh at 23/02/2022
 **/

inline fun <T> apiCall(
    block: () -> Response<T>
): T {
    val response = block()
    val body = response.body()
    return when (response.isSuccessful && body != null) {
        true -> {
            body
        }
        false ->  throw Error("Failed response")
    }
}
