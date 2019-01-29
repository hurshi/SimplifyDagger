package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.ActivityBean
import io.github.hurshi.simplifydagger.scopes.ActivityScope

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    internal fun providePerson(): ActivityBean {
        return ActivityBean("hello ActivityModule")
    }

}
