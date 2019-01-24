package io.github.hurshi.simplifydagger.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.simplifydagger.beans.Teacher
import io.github.hurshi.simplifydagger.scopes.ActivityScope

@Module
class SecModule {

    @ActivityScope
    @Provides
    internal fun provideMediaPlayer(): Teacher {
        return Teacher("hurshi teacher", 19)
    }
}