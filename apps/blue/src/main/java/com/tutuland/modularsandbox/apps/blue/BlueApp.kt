package com.tutuland.modularsandbox.apps.blue

import android.app.Application
import timber.log.Timber

class BlueApp : Application() {

    companion object {
        private lateinit var INSTANCE: BlueApp
        fun get(): BlueApp = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Timber.plant(Timber.DebugTree())
    }
}