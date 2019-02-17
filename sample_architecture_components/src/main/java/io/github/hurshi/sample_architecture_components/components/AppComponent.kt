package io.github.hurshi.sample_architecture_components.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.hurshi.sample_architecture_components.App
import io.github.hurshi.sample_architecture_components.modules.AppModule
import io.github.hurshi.sample_architecture_components.scopes.AppScope

@AppScope
@Component(
        modules = [AndroidSupportInjectionModule::class
            , AppModule::class
            , io.github.hurshi.sample_architecture_components.simplifydagger.AutoAndroidActivityScopeComponentInjector::class
            , io.github.hurshi.sample_architecture_components.simplifydagger.AutoViewModelAppScopeComponentInjector::class
        ]
)
abstract interface AppComponent : AndroidInjector<App> {

}