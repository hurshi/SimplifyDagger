package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.MainFragmentBean
import io.github.hurshi.sample_daggerandroid.scopes.FragmentScope

@Module
class MainFragment2Module {

    @FragmentScope
    @Provides
    internal fun provide(): MainFragmentBean {
        return MainFragmentBean("hello MainFragmentModule 22")
    }

}
