package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.MainActivityBean
import io.github.hurshi.sample_daggerandroid.beans.SecActivityBean
import io.github.hurshi.sample_daggerandroid.scopes.ActivityScope

@Module
class SecActivityModule {

    @ActivityScope
    @Provides
    internal fun provide(): SecActivityBean {
        return SecActivityBean("hello SecActivityModule")
    }

}
