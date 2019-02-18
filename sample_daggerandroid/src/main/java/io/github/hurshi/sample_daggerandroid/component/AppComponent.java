package io.github.hurshi.sample_daggerandroid.component;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.hurshi.sample_daggerandroid.App;
import io.github.hurshi.sample_daggerandroid.modules.simplifydagger.AutoAppScopeModule;
import io.github.hurshi.sample_daggerandroid.scopes.AppScope;
import io.github.hurshi.sample_daggerandroid.ui.simplifydagger.AutoAndroidActivityScopeComponentInjector;

@AppScope
@Component(modules = {AutoAppScopeModule.class
        , AndroidSupportInjectionModule.class
        , AutoAndroidActivityScopeComponentInjector.class
})
public interface AppComponent extends AndroidInjector<App> {
}
