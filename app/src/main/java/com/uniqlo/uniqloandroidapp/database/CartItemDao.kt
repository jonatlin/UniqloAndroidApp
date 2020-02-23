package com.uniqlo.uniqloandroidapp.database

import androidx.room.*

@Dao
abstract class CartItemDao {

    @Query("SELECT * FROM cart_item")
    abstract suspend fun getAll() : List<CartItemEntity>

    @Query("SELECT * FROM cart_item WHERE itemId=:id")
    abstract suspend fun getById(id: String): CartItemEntity?

    @Query("UPDATE cart_item SET quantity=quantity+1 WHERE itemId = :id")
    abstract suspend fun incrementQuantity(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(item: CartItemEntity)

    @Delete
    abstract suspend fun delete(item: CartItemEntity)

    // If another Item found with same ID increment. @Transaction allows to run off main thread.
    @Transaction
    open suspend fun insertOrUpdate(item: CartItemEntity) {
        val cartItemFromDb: CartItemEntity? = getById(item.itemId)
        if(cartItemFromDb==null)
            insert(item)
        else
            incrementQuantity(item.itemId)
    }



}