package com.example.domain.network.di

import com.example.domain.network.repository.CategoryRepository
import com.example.domain.network.repository.DishesRepository
import com.example.domain.network.usecases.GetCategories
import com.example.domain.network.usecases.GetDishes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(repo: CategoryRepository): GetCategories {
        return GetCategories(repository = repo)
    }

    @Provides
    @Singleton
    fun provideGetDishesUseCase(repo: DishesRepository): GetDishes {
        return GetDishes(repository = repo)
    }

}