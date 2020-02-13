package com.uniqlo.uniqloandroidapp.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class ItemEntity(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "itemId")
    val itemId: String,

    @ColumnInfo(name = "quantity")
    val quantity: Integer,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,

    @ColumnInfo(name = "size")
    val size: String,

    @ColumnInfo(name = "color")
    val color: String
) {
}