package com.example.domain.database.usecases.dish

import com.example.domain.database.repository.CachedDishRepository
import com.example.domain.network.model.Dish
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveCachedDish@Inject constructor(private val repository: CachedDishRepository) {
    suspend operator fun invoke(dish:Dish) {
        repository.saveCachedDish(dish)
    }
}