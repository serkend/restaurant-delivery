package com.example.data.database.repository

import com.example.data.database.dao.DishDao
import com.example.data.database.mappers.toData
import com.example.data.database.mappers.toDishesDomain
import com.example.domain.database.repository.CachedDishRepository
import com.example.domain.network.model.Dish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CachedDishRepositoryImpl @Inject constructor(private val dishDao: DishDao) :
    CachedDishRepository {
    override fun getCachedDishes(): Flow<List<Dish>> {
        return dishDao.getCachedDishes().map { it.toDishesDomain() }
    }

    override suspend fun saveCachedDish(cachedDishDTO: Dish) {
        dishDao.saveCachedDish(cachedDishDTO.toData())
    }

}