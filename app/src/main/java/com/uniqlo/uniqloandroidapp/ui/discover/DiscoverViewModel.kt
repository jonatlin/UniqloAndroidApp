package com.uniqlo.uniqloandroidapp.ui.discover

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.dropbox.android.external.store4.fresh
import com.dropbox.android.external.store4.get
import com.uniqlo.uniqloandroidapp.BR
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.UniqloApplication
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.data.Item
import com.uniqlo.uniqloandroidapp.respository.AdsRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class DiscoverViewModel(
    app: Application
) : ViewModel() {

    private val adsStore = (app as UniqloApplication).adsStore
    private val popularItemsStore = (app as UniqloApplication).popularItemsStore

    // mutable private so only ViewModel can change
    private val _adList = MutableLiveData<StoreResponse<List<Ad>>>(StoreResponse.Data(emptyList(), ResponseOrigin.Fetcher))
    val adList: LiveData<StoreResponse<List<Ad>>>
        get() = _adList

    private val _popularItemsList = MutableLiveData<StoreResponse<List<Item>>>(StoreResponse.Data(emptyList(), ResponseOrigin.Fetcher))
    val popularItemsList: LiveData<StoreResponse<List<Item>>>
        get() = _popularItemsList

    fun refreshAds() {

        // network/cache/db call coroutine scoped to ViewModel
        viewModelScope.launch {

            _adList.value = try {

                val data = adsStore.fresh("refresh")
                Timber.d("get ads: %s", data.toString())
                StoreResponse.Data(data, ResponseOrigin.Fetcher)
            } catch(e: Exception){
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }
        }
    }

    fun refreshPopularItems() {

        // network/cache/db call coroutine scoped to ViewModel
        viewModelScope.launch {

            _popularItemsList.value = try {

                val data = popularItemsStore.get("refresh")
                Timber.d("get popular items: %s", data.toString())
                StoreResponse.Data(data, ResponseOrigin.Fetcher)
            } catch(e: Exception){
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }
        }
    }

}