package com.uniqlo.uniqloandroidapp

import android.app.Application
import timber.log.Timber

class UniqloApplication : Application()  {

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}