package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.ActivityBean
import io.github.hurshi.sample_daggerandroid.scopes.ActivityScope

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    internal fun provide(): ActivityBean {
        return ActivityBean("hello ActivityModule")
    }

}
