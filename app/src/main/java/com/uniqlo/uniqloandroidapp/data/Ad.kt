package com.uniqlo.uniqloandroidapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ad constructor (

    @Json(name="AD_ITEM_ID")
    var adId: String = "0",
    @Json(name="AD_ITEM_IMAGE")
    var imageUrl: String= "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_MARIMEKKO_MERINO-BLEND_PANTS_Brown.jpg",
    @Json(name="DESCRIPTION_SHORT")
    var shortDescription: String="description",
    @Json(name="TEXT_COLOR")
    var textColor: String="ffffff",
    @Json(name="SHOW_TEXT")
    var showText: Int=0

)

@JsonClass(generateAdapter = true)
data class Ads(
    @Json(name="rows")
    val rows: List<Ad>
)