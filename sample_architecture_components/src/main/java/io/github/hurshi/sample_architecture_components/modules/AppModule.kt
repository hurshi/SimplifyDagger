package io.github.hurshi.sample_architecture_components.modules

import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_architecture_components.entity.DaoSessionSimulate
import io.github.hurshi.sample_architecture_components.scopes.AppScope

@Module
class AppModule {
    @AppScope
    @Provides
    internal fun providerDao(): DaoSessionSimulate {
        return DaoSessionSimulate("Hello, I am a DB")
    }
}