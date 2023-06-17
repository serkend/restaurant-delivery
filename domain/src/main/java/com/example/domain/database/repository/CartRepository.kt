package com.example.domain.database.repository

import com.example.domain.database.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCartItems() : Flow<List<CartItem>>
    suspend fun saveCartItem(cartItem: CartItem)
    suspend fun deleteCartItem(cartItem: CartItem)
}