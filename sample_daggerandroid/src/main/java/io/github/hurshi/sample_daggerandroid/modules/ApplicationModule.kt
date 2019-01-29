package io.github.hurshi.sample_daggerandroid.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.github.hurshi.sample_daggerandroid.beans.AppBean
import io.github.hurshi.sample_daggerandroid.scopes.AppScope

@Module
class ApplicationModule(private val appContext: Context) {

    @AppScope
    @Provides
    internal fun providePerson(): SharedPreferences {
        return appContext.getSharedPreferences("simplify_dagger_shared_preferences", Context.MODE_PRIVATE)
    }

    @AppScope
    @Provides
    internal fun provideAppBean(): AppBean {
        return AppBean("hurshi's phone", 188_8888_8888)
    }

}
