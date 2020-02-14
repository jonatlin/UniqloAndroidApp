package com.uniqlo.uniqloandroidapp.ui.discover

import android.app.Application
import androidx.lifecycle.*
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

    // store
    private val adsStore = (app as UniqloApplication).adsStore
    private val popularItemsStore = (app as UniqloApplication).popularItemsStore

    // LiveData
    private val _adList = MutableLiveData<StoreResponse<List<Ad>>>()
    val adList: LiveData<StoreResponse<List<Ad>>>
        get() = _adList

    private val _popularItemsList = MutableLiveData<StoreResponse<List<Item>>>()
    val popularItemsList: LiveData<StoreResponse<List<Item>>>
        get() = _popularItemsList

    private val _isLoading = MediatorLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        // whenever a network call finishes, check if all are done loading
        _isLoading.addSource(adList) {
                _isLoading.value = checkLoadingStatus()
                print(isLoading.value)
        }
        _isLoading.addSource(popularItemsList) {
            _isLoading.value = checkLoadingStatus()
            print(isLoading.value)
        }

    }

    private fun checkLoadingStatus(): Boolean {
        return (adList.value is StoreResponse.Loading<*> || popularItemsList.value is StoreResponse.Loading<*>)
    }

    fun refreshAds(isForce: Boolean = false) {

        // set state to loading
        _adList.value = StoreResponse.Loading(ResponseOrigin.Fetcher)

        // network/cache/db call as coroutine scoped to ViewModel
        viewModelScope.launch {
            _adList.value = try {
                // If specified, store will go directly to network. Otherwise use stale/refresh logic.
                val data = if (isForce) adsStore.fresh("refresh") else adsStore.get("refresh")
                Timber.d("get ads: %s", data.toString())
                StoreResponse.Data(data, ResponseOrigin.Fetcher)
            } catch (e: Exception) {
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }

        }
    }

    fun refreshPopularItems(isForce: Boolean = false) {

        // set state to loading
        _popularItemsList.value = StoreResponse.Loading(ResponseOrigin.Fetcher)

        // network/cache/db call as coroutine scoped to ViewModel
        viewModelScope.launch {

            _popularItemsList.value = try {

                val data =
                    if (isForce) popularItemsStore.fresh("refresh") else popularItemsStore.get("refresh")
                Timber.d("get popular items: %s", data.toString())
                StoreResponse.Data(data, ResponseOrigin.Fetcher)
            } catch (e: Exception) {
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }
        }
    }

}