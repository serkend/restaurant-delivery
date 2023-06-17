package com.example.data.network.api

import com.example.data.network.model.CategoriesDTO
import com.example.data.network.model.DishesDTO
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantApi {

    @GET("/v3/058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): Response<CategoriesDTO>

    @GET("/v3/aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun getDishes(): Response<DishesDTO>

}