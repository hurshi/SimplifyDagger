package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.MainActivityBean
import io.github.hurshi.sample_daggerandroid.scopes.ActivityScope

@Module
class MainActivityModule {

    @ActivityScope
    @Provides
    internal fun provide(): MainActivityBean {
        return MainActivityBean("hello MainActivityModule")
    }

}
