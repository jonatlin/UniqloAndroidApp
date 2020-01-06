package com.uniqlo.uniqloandroidapp.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DiscoverViewModelFactory () : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiscoverViewModel::class.java)) {
            return DiscoverViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}