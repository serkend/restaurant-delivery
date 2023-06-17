package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
)