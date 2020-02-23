package com.uniqlo.uniqloandroidapp.util

import android.content.Context
import com.google.gson.stream.JsonReader
import com.squareup.moshi.Moshi
import com.uniqlo.uniqloandroidapp.model.Item


class RandomItemGenerator {

    suspend fun generateItem(context: Context) : Item {

        // get from fts db instead
        /*context.assets.open("items.json").use{

                inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->

            Moshi().adapter()
        }
*/
        return Item()

    }

    /*fun generateItemList() : List<Item> {



        return Item()

    }*/

}