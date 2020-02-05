package com.uniqlo.uniqloandroidapp.util

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class SharedPreferencesLiveData<T>(val prefs: SharedPreferences,
                                            val key: String,
                                            val defaultValue: T) : LiveData<T>() {

    var listener: SharedPreferences.OnSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener {
                _, key ->
            if(key==this.key)
                value = getValueFromPreferences(key, defaultValue)
        }



    abstract fun getValueFromPreferences(key: String, defValue: T): T

    override fun onActive() {
        super.onActive()
        value = getValueFromPreferences(key, defaultValue)
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onInactive() {
        prefs.unregisterOnSharedPreferenceChangeListener(listener)
        super.onInactive()
    }
}

class SharedPreferencesStringLiveData(prefs: SharedPreferences, key: String, defValue: String) :
    SharedPreferencesLiveData<String>(prefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: String): String = prefs.getString(key, defValue) ?: ""
}