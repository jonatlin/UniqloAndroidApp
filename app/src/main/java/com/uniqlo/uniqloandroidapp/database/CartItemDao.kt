package com.uniqlo.uniqloandroidapp.database

import androidx.room.*

@Dao
abstract class CartItemDao {

    @Query("SELECT * FROM cart_item")
    abstract fun getAll() : List<ItemEntity>

    @Query("SELECT * FROM cart_item WHERE itemId=:id")
    abstract fun getById(id: String): ItemEntity?

    @Query("UPDATE cart_item SET quantity=quantity+1 WHERE itemId = :id")
    abstract fun incrementQuantity(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: ItemEntity)

    // If another Item found with same ID increment.
    fun insertOrUpdate(item: ItemEntity) {
        val itemFromDb: ItemEntity? = getById(item.itemId)
        if(itemFromDb==null)
            insert(item)
        else
            incrementQuantity(item.itemId)
    }



}