package com.uniqlo.uniqloandroidapp.ui.results

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.dropbox.android.external.store4.get
import com.uniqlo.uniqloandroidapp.UniqloApplication
import com.uniqlo.uniqloandroidapp.model.Item
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class ResultsViewModel(
    app: Application

) : ViewModel() {

    private val itemResultsStore = (app as UniqloApplication).itemResultsStore


    // mutable private so only ViewModel can change
    private val _itemList = MutableLiveData<StoreResponse<List<Item>>>(StoreResponse.Data(emptyList(), ResponseOrigin.Fetcher))
    val itemList: LiveData<StoreResponse<List<Item>>>
        get() = _itemList

    // need for databinding
    private val _itemCount =  MutableLiveData<Int>().apply{ value = 0}
    val itemCount: LiveData<Int>
        get() = _itemCount

    private val _resultsName =  MutableLiveData<String>().apply{ value = "Results"}
    val resultsName: LiveData<String>
        get() = _resultsName

//    val itemsRepository: ItemsRepository= ItemsRepository()

    fun updateResultItems(adId: String) {

        viewModelScope.launch {

            _itemList.value = try {

                val data = itemResultsStore.get(null to adId)
                Timber.d("get result items: %s", data.toString())

                val response = StoreResponse.Data(data, ResponseOrigin.Fetcher)
                _itemCount.value = response.value.rows.size
                _resultsName.value = response.value.name

                //awkward
                StoreResponse.Data(response.value.rows, ResponseOrigin.Fetcher)

            } catch(e: Exception){
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }


            /* _itemList.value = itemsRepository.getAdItems(id)
             _totalItems.value = _itemList.value?.size
             Timber.d("items: %d",  _totalItems.value)*/
        }




    }

}