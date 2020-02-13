package com.uniqlo.uniqloandroidapp.database

import androidx.room.*

@Dao
interface CartItemDao {

    @Query("SELECT * FROM cart_item")
    fun get() : List<ItemEntity>

    @Query("UPDATE cart_item SET quantity=:price WHERE itemId = :id")
    fun inserta(price: Float?, id: Int)

    @Update(entity = ItemEntity::class)
    fun update(item: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ItemEntity)

}