package com.example.domain.network.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Dish(
    val description: String,
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int
)