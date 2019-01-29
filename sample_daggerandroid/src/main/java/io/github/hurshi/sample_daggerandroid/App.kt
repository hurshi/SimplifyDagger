package io.github.hurshi.sample_daggerandroid

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.hurshi.sample_daggerandroid.component.DaggerAppComponent
import io.github.hurshi.sample_daggerandroid.modules.AppModule

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}