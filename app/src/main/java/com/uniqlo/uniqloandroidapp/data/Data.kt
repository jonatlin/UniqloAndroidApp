package com.uniqlo.uniqloandroidapp.data

import com.squareup.moshi.Json

data class Data(
    @Json(name="rows")
    val rows: List<Ad>
//    ,
//    @Json(name = "metadata")
//    val ads: List<Ad>
)