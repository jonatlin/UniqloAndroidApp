package com.uniqlo.uniqloandroidapp.ui.itemdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uniqlo.uniqloandroidapp.ui.results.ResultsViewModel

class ItemDetailsViewModelFactory (
    private val app: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemDetailsViewModel::class.java)) {
            return ItemDetailsViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}