package com.example.domain.database.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.network.model.Dish
import kotlinx.coroutines.flow.Flow

interface CachedDishRepository {
    fun getCachedDishes() : Flow<List<Dish>>

    suspend fun saveCachedDish(cachedDishDTO: Dish)

}