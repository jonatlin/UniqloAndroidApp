package com.uniqlo.uniqloandroidapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item constructor (

    @Json(name="ITEM_ID")
    var adId: String = "0",
    @Json(name="ITEM")
    var name: String = "shirt",
    @Json(name="ITEM_IMAGE")
    var imageUrl: String = "https://via.placeholder.com/300"

)

@JsonClass(generateAdapter = true)
data class Items(
    @Json(name="rows")
    val rows: List<Item>
)