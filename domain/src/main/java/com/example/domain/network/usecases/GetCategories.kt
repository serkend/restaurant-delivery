package com.example.domain.network.usecases

import com.example.common.ResponseStatus
import com.example.domain.network.model.Categories
import com.example.domain.network.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCategories @Inject constructor(private val repository: CategoryRepository) {
    suspend operator fun invoke(): Flow<ResponseStatus<Categories>> = flow {
        emit(ResponseStatus.Loading)
        try {
            val data = repository.getCategories()
            emit(ResponseStatus.Success(data = data))
        } catch (e: Exception) {
            emit(ResponseStatus.Error(e.message ?: "Some message"))
        }
    }
}