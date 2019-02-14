package io.github.hurshi.sample_architecture_components.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.hurshi.sample_architecture_components.MainActivity
import io.github.hurshi.sample_architecture_components.scopes.ActivityScope

@Module
abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}