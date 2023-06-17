package com.example.domain.database.usecases.cart

data class CartUseCases(
    val getCartItems: GetCartItems,
    val saveCartItem: SaveCartItem,
    val deleteCartItem: DeleteCartItem
)