package com.example.data.database.repository

import com.example.data.database.dao.CartDao
import com.example.data.database.mappers.toData
import com.example.data.database.mappers.toDomain
import com.example.domain.database.model.CartItem
import com.example.domain.database.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val dao: CartDao) : CartRepository {

    override fun getAllCartItems(): Flow<List<CartItem>> {
        return dao.getAllCartItems().map { it.toDomain() }
    }

    override suspend fun saveCartItem(cartItem: CartItem) {
        return dao.saveCartItem(cartItem.toData())
    }

    override suspend fun deleteCartItem(cartItem: CartItem) {
        return dao.deleteCartItem(cartItem.toData())
    }

}