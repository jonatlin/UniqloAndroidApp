package com.uniqlo.uniqloandroidapp.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItemEntity(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "itemId")
    val itemId: String="0",

    @ColumnInfo(name = "name")
    val name: String = "Shirt",

    @ColumnInfo(name = "price")
    val price: Float = 5.00f,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,

    @ColumnInfo(name = "quantity")
    val quantity: Int = 0,

    @ColumnInfo(name = "size")
    val size: String = "M",

    @ColumnInfo(name = "color")
    val color: String
) {
}