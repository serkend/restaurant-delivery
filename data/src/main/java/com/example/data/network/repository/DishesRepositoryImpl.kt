package com.example.data.network.repository

import com.example.data.network.api.RestaurantApi
import com.example.data.network.mappers.toDomain
import com.example.data.network.utils.SafeApiRequest
import com.example.domain.network.model.Dishes
import com.example.domain.network.repository.DishesRepository
import javax.inject.Inject

class DishesRepositoryImpl @Inject constructor(private val apiService: RestaurantApi) :
    DishesRepository, SafeApiRequest() {
    override suspend fun getDishes(): Dishes {
        val response = safeApiRequest { apiService.getDishes() }
        return response.toDomain()
    }
}