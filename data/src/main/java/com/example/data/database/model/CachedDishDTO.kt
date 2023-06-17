package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
data class CachedDishDTO(
    val description: String,
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int
)
