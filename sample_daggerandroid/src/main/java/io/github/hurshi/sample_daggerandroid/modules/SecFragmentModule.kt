package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.SecFragmentBean
import io.github.hurshi.sample_daggerandroid.scopes.FragmentScope

@Module
class SecFragmentModule {

    @FragmentScope
    @Provides
    internal fun provide(): SecFragmentBean {
        return SecFragmentBean("hello SecFragmentModule")
    }

}
