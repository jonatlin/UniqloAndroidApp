package com.uniqlo.uniqloandroidapp.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.uniqlo.uniqloandroidapp.util.SharedPreferencesStringLiveData

class MainViewModel(
    app: Application
) : ViewModel() {
   /* private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name*/

    val prefs = PreferenceManager.getDefaultSharedPreferences(app)

    var theme: SharedPreferencesStringLiveData = SharedPreferencesStringLiveData(prefs, "theme", "SYSTEM")



}