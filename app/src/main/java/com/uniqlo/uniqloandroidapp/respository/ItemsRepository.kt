package com.uniqlo.uniqloandroidapp.respository

import com.uniqlo.uniqloandroidapp.api.RetrofitFactory
import com.uniqlo.uniqloandroidapp.api.UniqloService
import com.uniqlo.uniqloandroidapp.data.Item
import retrofit2.Retrofit
import timber.log.Timber

class ItemsRepository {

    lateinit var uniqloService: UniqloService
    lateinit var retrofitClient: Retrofit

    // Non-store way
    init {
        retrofitClient = RetrofitFactory.retrofit("http://150.136.152.167:8000/")
        uniqloService = retrofitClient.create(UniqloService::class.java)
    }

    suspend fun getAdItems(id: String): List<Item>? {
        try {
            Timber.d("network call")
            val response = uniqloService.getAdItems(id)
//            val response2 = uniqloService.getAds()
            if (response.isSuccessful) {
                val body = response.body()
//                Timber.d(response2.body().toString())
                Timber.d(body.toString())
                return body?.rows

            } else {
                Timber.d(response.errorBody().toString())
            }

        } catch (e: Exception) {
            Timber.d(e.toString())
        }

        return emptyList()

    }

}