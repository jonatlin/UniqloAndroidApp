package com.uniqlo.uniqloandroidapp

import android.app.Application
import com.dropbox.android.external.store4.Store
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.data.Item
import com.uniqlo.uniqloandroidapp.respository.UniqloRepository
import timber.log.Timber

class UniqloApplication : Application()  {

    lateinit var adsStore: Store<String, List<Ad>>
    lateinit var popularItemsStore: Store<String, List<Item>>


    override fun onCreate() {
        super.onCreate()

        // DI?
        adsStore = UniqloRepository.createAdsStore(this)
        popularItemsStore = UniqloRepository.createPopularItemsStore()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}