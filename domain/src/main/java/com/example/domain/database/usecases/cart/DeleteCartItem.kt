package com.example.domain.database.usecases.cart

import com.example.domain.database.model.CartItem
import com.example.domain.database.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCartItem @Inject constructor(private val repository: CartRepository) {
    suspend operator fun invoke(cartItem: CartItem) {
        repository.deleteCartItem(cartItem)
    }
}