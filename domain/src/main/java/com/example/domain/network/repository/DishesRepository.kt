package com.example.domain.network.repository

import com.example.domain.network.model.Dishes
import retrofit2.Response

interface DishesRepository {
    suspend fun getDishes() : Dishes
}