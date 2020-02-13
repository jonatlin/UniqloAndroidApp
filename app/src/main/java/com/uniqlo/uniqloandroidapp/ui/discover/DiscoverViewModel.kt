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
import com.uniqlo.uniqloandroidapp.UniqloApplication
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.data.Item
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

    // TODO: use seperate class with flag for each coroutine?
    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun refreshAds(isForce: Boolean = false) {

        _isLoading.value = true

        // network/cache/db call coroutine scoped to ViewModel
        viewModelScope.launch {

            _adList.value = try {
                val data = if (isForce) adsStore.fresh("refresh") else adsStore.get("refresh")

                Timber.d("get ads: %s", data.toString())
                StoreResponse.Data(data, ResponseOrigin.Fetcher)
            } catch(e: Exception){
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }

            _isLoading.value = false


        }

    }

    fun refreshPopularItems(isForce: Boolean = false) {

        // network/cache/db call coroutine scoped to ViewModel
        viewModelScope.launch {

            _popularItemsList.value = try {

                val data = if (isForce) popularItemsStore.fresh("refresh") else popularItemsStore.get("refresh")
                Timber.d("get popular items: %s", data.toString())
                StoreResponse.Data(data, ResponseOrigin.Fetcher)
            } catch(e: Exception){
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }
        }
    }

}