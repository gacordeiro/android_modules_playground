package com.tutuland.modularsandbox.apps.red

import android.app.Application
import timber.log.Timber

class RedApp : Application() {

    companion object {
        private lateinit var INSTANCE: RedApp
        fun get(): RedApp = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Timber.plant(Timber.DebugTree())
    }
}