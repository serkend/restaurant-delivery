package com.example.data.database.di

import android.app.Application
import androidx.room.Room
import com.example.data.database.instances.CartDatabase
import com.example.data.database.instances.DishDataBase
import com.example.data.database.repository.CachedDishRepositoryImpl
import com.example.data.database.repository.CartRepositoryImpl
import com.example.domain.database.repository.CachedDishRepository
import com.example.domain.database.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCartDataBase(app: Application) = Room.databaseBuilder(
        app,
        CartDatabase::class.java,
        CartDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCartRepository(db: CartDatabase): CartRepository = CartRepositoryImpl(db.getDao())

    @Provides
    @Singleton
    fun provideDishDataBase(app: Application) = Room.databaseBuilder(
        app,
        DishDataBase::class.java,
        DishDataBase.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCachedDishRepository(db: DishDataBase): CachedDishRepository =
        CachedDishRepositoryImpl(db.getDao())

}