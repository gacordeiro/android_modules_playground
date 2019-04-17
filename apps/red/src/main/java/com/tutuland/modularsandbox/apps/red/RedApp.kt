package com.tutuland.modularsandbox.apps.red

import android.app.Activity
import android.app.Application
import com.tutuland.modularsandbox.apps.red.dagger.DaggerRedAppComponent
import com.tutuland.modularsandbox.apps.red.dagger.RedAppComponent
import com.tutuland.modularsandbox.apps.red.dagger.RedAppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class RedApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    private lateinit var component: RedAppComponent

    companion object {
        private lateinit var INSTANCE: RedApp
        fun get(): RedApp = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Timber.plant(Timber.DebugTree())
        component = DaggerRedAppComponent.builder()
            .redAppModule(RedAppModule(this))
            .build()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}