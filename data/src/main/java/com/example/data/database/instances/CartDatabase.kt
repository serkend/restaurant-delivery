package com.example.data.database.instances

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.CartDao
import com.example.data.database.model.CartItemDTO

@Database(entities = [CartItemDTO::class], version = 2)
abstract class CartDatabase : RoomDatabase() {

    abstract fun getDao() : CartDao

    companion object {
        const val DATABASE_NAME = "cart_db"
    }
}