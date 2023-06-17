package com.example.domain.database.model

data class CartItem(
    val id: Int,
    val quantity: Int,
    val price: Int,
    val imageUrl : String,
    val weight : Int,
    val name:String
)