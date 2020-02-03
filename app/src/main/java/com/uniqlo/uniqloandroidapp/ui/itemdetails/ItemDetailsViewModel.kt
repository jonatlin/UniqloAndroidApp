package com.uniqlo.uniqloandroidapp.ui.itemdetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.dropbox.android.external.store4.get
import com.uniqlo.uniqloandroidapp.UniqloApplication
import com.uniqlo.uniqloandroidapp.data.Item
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class ItemDetailsViewModel(
    app: Application

) : ViewModel() {

    private val itemResultsStore = (app as UniqloApplication).itemResultsStore

    private val _item = MutableLiveData<StoreResponse<Item>>()
    val item: LiveData<StoreResponse<Item>>
        get() = _item

    fun updateItem(itemId: String) {
        viewModelScope.launch {

            _item.value = try {

                val data = itemResultsStore.get(itemId to null)
                Timber.d("get result items: %s", data.toString())

                StoreResponse.Data(data.rows[0], ResponseOrigin.Fetcher)


            } catch (e: Exception) {
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }

        }

    }

}