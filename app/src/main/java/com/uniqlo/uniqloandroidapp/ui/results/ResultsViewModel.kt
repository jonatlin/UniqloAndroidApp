package com.uniqlo.uniqloandroidapp.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.data.Item
import com.uniqlo.uniqloandroidapp.respository.AdsRepository
import com.uniqlo.uniqloandroidapp.respository.ItemsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class ResultsViewModel(

) : ViewModel() {

    private val _itemList = MutableLiveData<List<Item>>().apply { value = emptyList() }
    val itemList: LiveData<List<Item>>
        get() = _itemList

    val itemsRepository: ItemsRepository= ItemsRepository()

    fun updateItems(id: String) {

        viewModelScope.launch {

            _itemList.value = itemsRepository.getAdItems(id)
        }
        Timber.d(_itemList.value.toString())

    }

}