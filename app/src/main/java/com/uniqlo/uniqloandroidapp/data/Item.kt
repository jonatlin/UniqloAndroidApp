package com.uniqlo.uniqloandroidapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item constructor(

    @Json(name = "ITEM_ID")
    var itemId: String = "0",
    @Json(name = "ITEM")
    var name: String = "MEN FLEECE HALF ZIP PULLOVER BLUE",
    @Json(name = "ITEM_IMAGE")
    var imageUrl: String = "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/MEN_FLEECE_HALF-ZIP_PULLOVER_Blue.jpg",
    @Json(name = "ORIGINAL_PRICE")
    var originalPrice: Float? = 10f,
    @Json(name = "DISCOUNT_PRICE")
    var discountPrice: Float? = 5f,
    var favorite: Boolean = false,
    @Json(name = "DESCRIPTION")
    var description: String = "Description"
    )

@JsonClass(generateAdapter = true)
data class AdItems(
    @Json(name = "rows")
    val rows: List<Item>,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class Items(
    @Json(name = "rows")
    val rows: List<Item>,
    @Json(name = "name")
    val name: String = "Results"
)