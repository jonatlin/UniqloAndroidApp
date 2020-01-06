package com.uniqlo.uniqloandroidapp.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.respository.AdsRepository
import kotlinx.coroutines.launch

class DiscoverViewModel(


) : ViewModel() {

    private val _adList = MutableLiveData<List<Ad>>().apply { value = emptyList() }
    val adList:LiveData<List<Ad>>
        get()= _adList

    val adsRepository: AdsRepository = AdsRepository()


  /*  init{
        _adList.value = mutableListOf(Ad(0, "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_Men_Dress_Shirts.jpg")
//            ,Ad(1,"https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_Men_HEATTECH_COLLECTION.jpg" )
            , Ad(2, "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_Women_AIRISM_Collection.jpg"))
//        Log.d("asdfasdf",adList.value?.get(0)?.imageUrl)
    }*/

    fun updateAds() {

        viewModelScope.launch {

            _adList.value = adsRepository.getAds()
        }




    }

}