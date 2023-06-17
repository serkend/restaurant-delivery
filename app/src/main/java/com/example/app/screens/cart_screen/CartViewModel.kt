package com.example.app.screens.cart_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.database.model.CartItem
import com.example.domain.database.usecases.cart.CartUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases
) : ViewModel() {
    private var _uiState: MutableStateFlow<List<CartItem>> =
        MutableStateFlow(emptyList())
    val uiState = _uiState.asStateFlow()

    var totalPrice = mutableStateOf(0)
    private set

    init {
        getAllCartItems()
    }

    private fun getAllCartItems() {
        viewModelScope.launch {
            cartUseCases.getCartItems().collect { list ->
                Log.e("TAG", "getAllCartItems: ${list}", )
                _uiState.value = list
                totalPrice.value = list.sumOf { it.price * it.quantity }

            }
        }
    }

    fun addToCartDish(cartItem: CartItem) {
        viewModelScope.launch {
            cartUseCases.saveCartItem(cartItem)
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            cartUseCases.deleteCartItem(cartItem)
        }
    }


}