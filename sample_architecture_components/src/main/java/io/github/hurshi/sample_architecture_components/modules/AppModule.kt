package io.github.hurshi.sample_architecture_components.modules

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_architecture_components.entity.DaoSessionSimulate
import io.github.hurshi.sample_architecture_components.scopes.AppScope
import io.github.hurshi.sample_architecture_components.vm_factory.VMFactory

@Module(includes = [AppModule.AbstractAppModule::class])
class AppModule {
    @AppScope
    @Provides
    internal fun providerDao(): DaoSessionSimulate {
        return DaoSessionSimulate("Hello, I am a DB")
    }

    @Module
    abstract class AbstractAppModule {
        @AppScope
        @Binds
        abstract fun bindFactory(factory: VMFactory): ViewModelProvider.Factory
    }
}