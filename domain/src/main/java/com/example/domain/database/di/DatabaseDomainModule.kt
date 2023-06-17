package com.example.domain.database.di

import com.example.domain.database.repository.CachedDishRepository
import com.example.domain.database.repository.CartRepository
import com.example.domain.database.usecases.cart.CartUseCases
import com.example.domain.database.usecases.cart.DeleteCartItem
import com.example.domain.database.usecases.cart.GetCartItems
import com.example.domain.database.usecases.cart.SaveCartItem
import com.example.domain.database.usecases.dish.DishUseCases
import com.example.domain.database.usecases.dish.GetCachedDishes
import com.example.domain.database.usecases.dish.SaveCachedDish
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseDomainModule {

    @Provides
    @Singleton
    fun provideCartUseCases(repo: CartRepository): CartUseCases {
        return CartUseCases(
            getCartItems = GetCartItems(repo),
            saveCartItem = SaveCartItem(repo),
            deleteCartItem = DeleteCartItem(repo)
        )
    }

    @Provides
    @Singleton
    fun provideDishUseCases(repo: CachedDishRepository): DishUseCases {
        return DishUseCases(
            getCachedDishes = GetCachedDishes(repo),
            saveCachedDish = SaveCachedDish(repo)
        )
    }

}