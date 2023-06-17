package com.example.domain.network.repository

import com.example.domain.network.model.Categories

interface CategoryRepository {
    suspend fun getCategories() : Categories
}