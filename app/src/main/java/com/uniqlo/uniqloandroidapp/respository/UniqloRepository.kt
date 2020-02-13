package com.uniqlo.uniqloandroidapp.respository

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
import com.dropbox.android.external.store4.StoreResponse
import com.squareup.moshi.Moshi
import com.uniqlo.uniqloandroidapp.UniqloApplication
import com.uniqlo.uniqloandroidapp.api.UniqloService
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.data.Item
import com.uniqlo.uniqloandroidapp.data.Items
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

@UseExperimental(FlowPreview::class, ExperimentalCoroutinesApi::class)
object UniqloRepository {

//    private val moshi = Moshi.Builder().build()

    // get list of ads for discover page
    fun createAdsStore(app: UniqloApplication): Store<String, List<Ad>> {

        // Nonflow since HTTP single request vs data stream
        return StoreBuilder.fromNonFlow<String, List<Ad>> {

            createRetrofit().getAds().body()?.rows ?: mutableListOf(Ad(),Ad())
        }
            .build()
    }

    fun createItemResultsStore(): Store<Pair<String?, String?>, Items> {

        // Nonflow since HTTP single request vs websocket
        return StoreBuilder.fromNonFlow<Pair<String?, String?>, Items> {

                (itemId, adId) ->

            (createRetrofit().getItems(itemId=itemId, adId=adId).body() ?: Items(emptyList()))
        }
            .build()
    }

    // get list of popular items
    fun createPopularItemsStore(): Store<String, List<Item>> {
        return StoreBuilder.fromNonFlow<String, List<Item>> {

            createRetrofit().getPopularItems(null).body()?.rows ?: emptyList()
        }
            .cachePolicy(
            MemoryPolicy.builder()
                .setMemorySize(5)
                .setExpireAfterAccess(10) // or setExpireAfterWrite(10)
                .setExpireAfterTimeUnit(TimeUnit.SECONDS)
                .build()
        )
            .build()
    }

    fun createRetrofit(): UniqloService {

        val baseUrl = "http://150.136.152.167:8000/"

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(5L, TimeUnit.SECONDS)
            .readTimeout(5L, TimeUnit.SECONDS)
            .callTimeout(5L, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UniqloService::class.java)

    }

/*    val speakers = conferenceData.speakers.map {
        SpeakerFtsEntity(
            speakerId = it.id,
            name = it.name,
            description = it.biography
        )
    }
    appDatabase.speakerFtsDao().insertAll(speakers)*/

}
