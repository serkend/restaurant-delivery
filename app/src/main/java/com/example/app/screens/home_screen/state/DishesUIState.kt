package com.example.app.screens.home_screen.state

sealed class DishesUIState<out T> {
    data class ShowSnackbar(val error: String) : DishesUIState<Nothing>()
    //  data class Success<T>(val data : T) : DishesUIState<T>()
    object Loading : DishesUIState<Nothing>()
}