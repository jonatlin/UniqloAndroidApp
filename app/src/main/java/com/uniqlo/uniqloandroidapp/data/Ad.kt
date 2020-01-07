package com.uniqlo.uniqloandroidapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ad constructor (

    @Json(name="AD_ITEM_ID")
    var adId: Int = 0,
    @Json(name="AD_ITEM_IMAGE")
    var imageUrl: String= "http://www.cfd-art.com/wp-content/uploads/2019/01/why-vertical-300x300.jpg",
    @Json(name="DESCRIPTION_SHORT")
    var shortDescription: String,
    @Json(name="TEXT_COLOR")
    var textColor: String,
    @Json(name="SHOW_TEXT")
    var showText: Int

) {

    init {


//        adId = (Math.random() * 4).toInt()
    }

}