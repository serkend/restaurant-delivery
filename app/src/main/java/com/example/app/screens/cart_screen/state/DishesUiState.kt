package com.example.app.screens.cart_screen.state

sealed class CartUiState<out T> {
    data class ShowSnackbar(val error : String) : CartUiState<Nothing>()
    data class Success<T>(val data : T) : CartUiState<T>()
    object Loading : CartUiState<Nothing>()
}