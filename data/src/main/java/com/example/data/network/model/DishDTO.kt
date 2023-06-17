package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class DishDTO(
    val description: String,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int
)