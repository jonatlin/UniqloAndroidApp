package com.uniqlo.uniqloandroidapp.data

data class Ad constructor (
    var adId: Int = 0,
    var imageUrl: String= "http://www.cfd-art.com/wp-content/uploads/2019/01/why-vertical-300x300.jpg"
) {

    init {


//        adId = (Math.random() * 4).toInt()
    }

}