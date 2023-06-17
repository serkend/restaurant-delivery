package com.example.domain.database.usecases.cart

import com.example.domain.database.model.CartItem
import com.example.domain.database.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItems @Inject constructor(private val repository: CartRepository) {
    operator fun invoke(): Flow<List<CartItem>> {
        return repository.getAllCartItems()
    }
}