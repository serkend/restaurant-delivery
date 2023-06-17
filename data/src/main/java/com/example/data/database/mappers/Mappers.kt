package com.example.data.database.mappers

import com.example.data.database.model.CachedDishDTO
import com.example.data.database.model.CartItemDTO
import com.example.domain.database.model.CartItem
import com.example.domain.network.model.Dish

fun List<CartItemDTO>.toDomain(): List<CartItem> {
    return this.map { it.toDomain() }
}

fun CartItemDTO.toDomain(): CartItem {
    return CartItem(
        id = this.id,
        quantity = this.quantity,
        price = this.price,
        imageUrl = this.imageUrl,
        weight = this.weight,
        name = this.name
    )
}

fun CartItem.toData(): CartItemDTO {
    return CartItemDTO(
        id = this.id,
        quantity = this.quantity,
        price = this.price,
        imageUrl = this.imageUrl,
        weight = this.weight,
        name = this.name
    )
}

fun List<CachedDishDTO>.toDishesDomain(): List<Dish> {
    return this.map {
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
}

fun Dish.toData(): CachedDishDTO {
    return CachedDishDTO(
        id = this.id,
        imageUrl = this.imageUrl,
        description = this.description,
        name = this.name,
        price = this.price,
        tegs = this.tegs,
        weight = this.weight
    )

}

