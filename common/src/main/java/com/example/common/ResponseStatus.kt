package com.example.common

sealed class ResponseStatus<out T> {
    object Loading : ResponseStatus<Nothing>()
    data class Success<T>(val data : T) : ResponseStatus<T>()
    data class Error(val error : String) : ResponseStatus<Nothing>()
}

