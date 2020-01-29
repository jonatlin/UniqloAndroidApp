package com.uniqlo.uniqloandroidapp.respository

import com.uniqlo.uniqloandroidapp.api.RetrofitFactory
import com.uniqlo.uniqloandroidapp.api.UniqloService
import com.uniqlo.uniqloandroidapp.data.Ad
import retrofit2.Retrofit
import timber.log.Timber
import java.lang.Exception

// Using Store instead
class AdsRepository {

    var uniqloService: UniqloService
    var retrofitClient: Retrofit

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

    }


}