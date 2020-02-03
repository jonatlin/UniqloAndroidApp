package com.uniqlo.uniqloandroidapp.api

import com.uniqlo.uniqloandroidapp.data.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface UniqloService{

    @GET("getAds")
    suspend fun getAds(
//        @Query("id") id: Int?,
//        @Query("limit") limit: Int?,
//        @Query("s") sort: String
    ): Response<Ads>

    @GET("getAdItems")
    suspend fun getAdItems(
        @Query("id") id: String
    ): Response<AdItems>

    @GET("getItems")
    suspend fun getItems(
        @Query("adId") adId: String?=null,
        @Query("itemId") itemId: String?=null
    ): Response<Items>

    @GET("getPopularItems")
    suspend fun getPopularItems(
        @Query("limit") limit: Int?
    ): Response<Items>

    companion object {

//        const val ENDPOINT = "http://150.136.152.167:8000/"

    }

}