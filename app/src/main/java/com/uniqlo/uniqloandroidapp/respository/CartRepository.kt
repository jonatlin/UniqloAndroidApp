package com.uniqlo.uniqloandroidapp.respository

import android.content.Context
import com.uniqlo.uniqloandroidapp.database.AppDatabase
import com.uniqlo.uniqloandroidapp.database.ItemEntity
import com.uniqlo.uniqloandroidapp.model.Item

object CartRepository {

    fun addItemToCart(context: Context, item: Item) {
        val itemEntity: ItemEntity = item.let {
            ItemEntity(
                itemId=it.itemId,
                quantity=1,
                imageUrl=it.imageUrl,
                size="M",
                color="Blue"
            )
        }
        AppDatabase.getInstance(context).cartItemDao().insertOrUpdate(itemEntity)
    }

}