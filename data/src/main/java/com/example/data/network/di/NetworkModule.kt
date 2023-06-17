package com.example.data.network.di

import com.example.data.network.api.RestaurantApi
import com.example.data.network.repository.CategoryRepositoryImpl
import com.example.data.network.repository.DishesRepositoryImpl
import com.example.domain.network.repository.CategoryRepository
import com.example.domain.network.repository.DishesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://run.mocky.io/v3/"

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(apiService : RestaurantApi): CategoryRepository {
        return CategoryRepositoryImpl(apiService = apiService)
    }

    @Provides
    @Singleton
    fun provideDishesRepository(apiService : RestaurantApi): DishesRepository {
        return DishesRepositoryImpl(apiService = apiService)
    }
}