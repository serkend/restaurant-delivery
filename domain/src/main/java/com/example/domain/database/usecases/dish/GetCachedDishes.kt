package com.example.domain.database.usecases.dish

import com.example.domain.database.repository.CachedDishRepository
import com.example.domain.network.model.Dish
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachedDishes @Inject constructor(private val repository: CachedDishRepository) {
    operator fun invoke(): Flow<List<Dish>> {
        return repository.getCachedDishes()
    }
}