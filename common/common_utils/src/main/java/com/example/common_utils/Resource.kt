package com.example.common_utils

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    class Success<T>(val data: T?) : Resource<T>()
    class Error<T>(val errorMessage: String, val data: T? = null) : Resource<T>()
}
