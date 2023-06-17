package com.example.data.network.repository

import com.example.common.ResponseStatus
import com.example.data.network.api.RestaurantApi
import com.example.data.network.mappers.toDomain
import com.example.data.network.model.CategoriesDTO
import com.example.data.network.utils.SafeApiRequest
import com.example.domain.network.model.Categories
import com.example.domain.network.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val apiService: RestaurantApi) :
    CategoryRepository, SafeApiRequest() {
    override suspend fun getCategories(): Categories {
        val response = safeApiRequest { apiService.getCategories() }
        return response.toDomain()
    }
}