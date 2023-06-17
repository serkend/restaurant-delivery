package com.example.data.database.instances

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.dao.CartDao
import com.example.data.database.dao.DishDao
import com.example.data.database.model.CachedDishDTO
import com.example.data.database.model.CartItemDTO
import com.example.data.database.utils.ListConverter

@Database(entities = [CachedDishDTO::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class DishDataBase : RoomDatabase() {

    abstract fun getDao() : DishDao
    companion object {
        const val DATABASE_NAME = "dish_db"
    }
}