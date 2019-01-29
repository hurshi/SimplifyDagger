package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.ActivityBean
import io.github.hurshi.sample_daggerandroid.beans.FragmentBean
import io.github.hurshi.simplifydagger.scopes.ActivityScope

@Module
class FragmentModule {

    @ActivityScope
    @Provides
    internal fun provide(): FragmentBean {
        return FragmentBean("hello FragmentModule")
    }

}
