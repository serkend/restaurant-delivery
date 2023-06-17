package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.data.database.model.CartItemDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_item")
    fun getAllCartItems() : Flow<List<CartItemDTO>>

    @Insert(onConflict = REPLACE)
    suspend fun saveCartItem(cartItemDTO: CartItemDTO)

    @Delete
    suspend fun deleteCartItem(cartItemDTO: CartItemDTO)
}