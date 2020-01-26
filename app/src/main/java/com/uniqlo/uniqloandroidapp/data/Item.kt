package com.uniqlo.uniqloandroidapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item constructor (

    @Json(name="ITEM_ID")
    var itemId: String = "0",
    @Json(name="ITEM")
    var name: String = "shirt",
    @Json(name="ITEM_IMAGE")
    var imageUrl: String = "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_Men_Dress_Shirts.jpg",
    var favorite: Boolean = false
)

@JsonClass(generateAdapter = true)
data class Items(
    @Json(name="rows")
    val rows: List<Item>
)