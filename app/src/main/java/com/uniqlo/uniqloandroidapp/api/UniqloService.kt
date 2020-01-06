package com.uniqlo.uniqloandroidapp.api

import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.data.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UniqloService{

    @GET("getAds")
    suspend fun getAds(
//        @Query("id") id: Int?,
//        @Query("limit") limit: Int?,
//        @Query("s") sort: String
    ): Response<Data>

    companion object {

        const val ENDPOINT = "http://150.136.152.167:8000/"
    }

}