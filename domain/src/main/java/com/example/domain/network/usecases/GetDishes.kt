package com.example.domain.network.usecases

import com.example.common.ResponseStatus
import com.example.domain.network.model.Dishes
import com.example.domain.network.repository.DishesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDishes @Inject constructor(private val repository: DishesRepository) {
    suspend operator fun invoke(): Flow<ResponseStatus<Dishes>> = flow {
        emit(ResponseStatus.Loading)
        try {
            val data = repository.getDishes()
            emit(ResponseStatus.Success(data = data))
        } catch (e: Exception) {
            emit(ResponseStatus.Error(e.message ?: "Some message"))
        }
    }
}