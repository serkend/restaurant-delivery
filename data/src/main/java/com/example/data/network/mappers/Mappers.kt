package com.example.data.network.mappers

import com.example.data.network.model.CategoriesDTO
import com.example.data.network.model.DishesDTO
import com.example.domain.network.model.Categories
import com.example.domain.network.model.Category
import com.example.domain.network.model.Dish
import com.example.domain.network.model.Dishes

fun CategoriesDTO.toDomain(): Categories {
    val domainCategories = categories.map {
        Category(
            id = it.id,
            imageUrl = it.imageUrl,
            name = it.name
        )
    }
    return Categories(categories = domainCategories)
}

fun DishesDTO.toDomain(): Dishes {
    val domainCategories = dishes.map {
        Dish(
            id = it.id,
            imageUrl = it.imageUrl,
            description = it.description,
            name = it.name,
            price = it.price,
            tegs = it.tegs,
            weight = it.weight
        )
    }
    return Dishes(dishes = domainCategories)
}