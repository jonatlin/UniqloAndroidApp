package com.uniqlo.uniqloandroidapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
CartItemEntity::class
],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    // singleton
    // TODO Currently Sunflower implementation. Use DI instead.
    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, AppDatabase::class.java, "APP_DATABASE")
                    .build().also{ instance = it}}
            }

    }


}