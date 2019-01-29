package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.FragmentBean
import io.github.hurshi.sample_daggerandroid.scopes.ActivityScope
import io.github.hurshi.sample_daggerandroid.scopes.FragmentScope

@Module
class FragmentModule {

    @FragmentScope
    @Provides
    internal fun provide(): FragmentBean {
        return FragmentBean("hello FragmentModule")
    }

}
