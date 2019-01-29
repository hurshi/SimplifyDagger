package io.github.hurshi.sample_daggerandroid.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.Person
import io.github.hurshi.simplifydagger.scopes.ActivityScope

@Module
class MainActivityModule {

    @ActivityScope
    @Provides
    internal fun providePerson(): Person {
        return Person("hurshi", 18)
    }

}
