package com.uniqlo.uniqloandroidapp.respository

import android.util.Log
import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.uniqlo.uniqloandroidapp.api.RetrofitFactory
import com.uniqlo.uniqloandroidapp.api.UniqloService
import com.uniqlo.uniqloandroidapp.data.Ad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class AdsRepository(
//    private val uniqloService: UniqloService
) {

    lateinit var uniqloService: UniqloService
    lateinit var retrofitClient: Retrofit

   init {
       retrofitClient = RetrofitFactory.retrofit("http://150.136.152.167:8000/")
       uniqloService = retrofitClient.create(UniqloService::class.java)

   }

    suspend fun getAds(): List<Ad>? {
        try {
            val response = uniqloService.getAds()
            if (response.isSuccessful) {
                val body = response.body()

                Log.d("abcabc", body.toString())
                return body?.rows
            } else {
                Log.d("MainActivity ", response.errorBody().toString())
            }
//        return null
        } catch(e: Exception) {
            Log.d("asdfasdf", e.toString());
        }

        return emptyList()
        /*return mutableListOf(Ad(0, "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_Men_Dress_Shirts.jpg")
            ,Ad(1,"https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_Men_HEATTECH_COLLECTION.jpg" ),
            Ad(2, "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_Women_AIRISM_Collection.jpg"))*/

    }


}