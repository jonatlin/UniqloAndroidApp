package com.uniqlo.uniqloandroidapp.respository

import com.uniqlo.uniqloandroidapp.api.RetrofitFactory
import com.uniqlo.uniqloandroidapp.api.UniqloService
import com.uniqlo.uniqloandroidapp.data.Ad
import retrofit2.Retrofit
import timber.log.Timber
import java.lang.Exception

class AdsRepository {

    var uniqloService: UniqloService
    var retrofitClient: Retrofit

    // Shouldn't have multiple retrofit clients? Dagger?
   init {
       retrofitClient = RetrofitFactory.retrofit("http://150.136.152.167:8000/")
       uniqloService = retrofitClient.create(UniqloService::class.java)

   }

    // suspend for viewmodel+coroutine compatibility
    suspend fun getAds(): List<Ad>? {
        try {
            Timber.d("network call")
            val response = uniqloService.getAds()
            if (response.isSuccessful) {
                val body = response.body()

                Timber.d(body.toString())
                return body?.rows

            } else {
                Timber.d(response.errorBody().toString())
            }

        } catch(e: Exception) {
            Timber.d(e.toString())
        }

        return emptyList()

        // testing
       /* return mutableListOf(Ad.kt(20, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1),
                Ad.kt(21, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1),
            Ad.kt(22, "https://onlinepngtools.com/images/examples-onlinepngtools/palm-fronds-and-sky.jpg", "asdf","000000",1),
            Ad.kt(23, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1),
            Ad.kt(24, "http://s3.amazonaws.com/images.seroundtable.com/google-css-images-1515761601.jpg", "asdf","000000",1),
            Ad.kt(25, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1),
            Ad.kt(25, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1),
            Ad.kt(25, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1),
            Ad.kt(25, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1),
            Ad.kt(25, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1),
            Ad.kt(24, "http://s3.amazonaws.com/images.seroundtable.com/google-css-images-1515761601.jpg", "asdf","000000",1),
            Ad.kt(24, "http://s3.amazonaws.com/images.seroundtable.com/google-css-images-1515761601.jpg", "asdf","000000",1),
            Ad.kt(26, "https://images-na.ssl-images-amazon.com/images/I/711YZCeSW-L._AC_UX679_.jpg", "asdf","000000",1))*/


    }


}