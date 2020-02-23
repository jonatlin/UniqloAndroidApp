package com.uniqlo.uniqloandroidapp.respository

import android.content.Context
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
import com.uniqlo.uniqloandroidapp.UniqloApplication
import com.uniqlo.uniqloandroidapp.database.AppDatabase
import com.uniqlo.uniqloandroidapp.database.CartItemEntity
import com.uniqlo.uniqloandroidapp.model.Item
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@UseExperimental(FlowPreview::class, ExperimentalCoroutinesApi::class)
object CartRepository {

    suspend fun addItemToCart(context: Context, item: Item) {
        val cartItemEntity: CartItemEntity = item.let {
            CartItemEntity(
                itemId=it.itemId,
                name = it.name,
                imageUrl=it.imageUrl,
                price=it.originalPrice,
                quantity=1,
                size="M",
                color="Blue"
            )
        }
        AppDatabase.getInstance(context).cartItemDao().insertOrUpdate(cartItemEntity)
    }

    suspend fun removeItemFromCart(context: Context, item: CartItemEntity) {
        AppDatabase.getInstance(context).cartItemDao().delete(item)

    }

        fun createCartItemsStore(app: UniqloApplication): Store<String, List<CartItemEntity>> {
        val db = AppDatabase.getInstance(app)

        // TODO change to flow so add to cart will reflect changes
        return StoreBuilder.fromNonFlow<String, List<CartItemEntity>> {

            // actually scenario is network call to get cart info. Instead from DB.
            db.cartItemDao().getAll()?: emptyList()
        }
                // persister would store cart info from network
            /*.persister(
                reader=db.cartItemDao().getAll()
            )*/

            .build()

    }

}