package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class CategoriesDTO(
    @SerializedName("сategories")
    val categories : List<CategoryDTO>
)
