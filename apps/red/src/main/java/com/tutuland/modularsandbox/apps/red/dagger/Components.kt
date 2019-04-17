package com.tutuland.modularsandbox.apps.red.dagger

import com.tutuland.modularsandbox.apps.red.RedApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RedAppModule::class])
interface RedAppComponent {
    fun inject(target: RedApp)
}