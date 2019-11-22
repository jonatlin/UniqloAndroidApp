package com.uniqlo.uniqloandroidapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class WomenMenKids {
    WOMEN, MEN, KIDS
}

class HomeViewModel(


) : ViewModel() {

    // save section
    private val _section = MutableLiveData<WomenMenKids>().apply { value = WomenMenKids.WOMEN }
    val section: LiveData<WomenMenKids>
        get()= _section


}