package com.tutuland.modularsandbox.apps.blue

import android.app.Activity
import android.app.Application
import com.tutuland.modularsandbox.apps.blue.dagger.BlueAppComponent
import com.tutuland.modularsandbox.apps.blue.dagger.BlueAppModule
import com.tutuland.modularsandbox.apps.blue.dagger.DaggerBlueAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class BlueApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    private lateinit var component: BlueAppComponent

    companion object {
        private lateinit var INSTANCE: BlueApp
        fun get(): BlueApp = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Timber.plant(Timber.DebugTree())

        component = DaggerBlueAppComponent.builder()
            .blueAppModule(BlueAppModule(this))
            .build()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}