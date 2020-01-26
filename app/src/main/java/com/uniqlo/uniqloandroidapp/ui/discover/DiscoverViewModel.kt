package com.uniqlo.uniqloandroidapp.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniqlo.uniqloandroidapp.BR
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.respository.AdsRepository
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding

class DiscoverViewModel(

) : ViewModel() {

    private val _adList = MutableLiveData<List<Ad>>().apply { value = emptyList() }
    val adList: LiveData<List<Ad>>
        get() = _adList

    val adsRepository: AdsRepository = AdsRepository()


    fun updateAds() {

        // network call coroutine scoped to viewmodel so process tied to lifecycle.
        viewModelScope.launch {

            _adList.value = adsRepository.getAds()
        }
    }

}