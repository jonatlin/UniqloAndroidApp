package com.uniqlo.uniqloandroidapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
ItemEntity::class
],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao



}