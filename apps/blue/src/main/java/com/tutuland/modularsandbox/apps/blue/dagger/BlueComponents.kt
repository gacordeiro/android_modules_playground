package com.tutuland.modularsandbox.apps.blue.dagger

import com.tutuland.modularsandbox.apps.blue.BlueApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BlueAppModule::class])
interface BlueAppComponent {
    fun inject(target: BlueApp)
}