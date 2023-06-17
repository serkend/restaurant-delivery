package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItemDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quantity: Int,
    val price: Int,
    val imageUrl : String,
    val weight : Int,
    val name:String
)