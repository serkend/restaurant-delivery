package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.CachedDishDTO
import com.example.data.database.model.CartItemDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Query("SELECT * FROM dish")
    fun getCachedDishes() : Flow<List<CachedDishDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCachedDish(cachedDishDTO: CachedDishDTO)

}