package io.github.hurshi.sample_architecture_components

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.hurshi.sample_architecture_components.components.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create()
    }
}