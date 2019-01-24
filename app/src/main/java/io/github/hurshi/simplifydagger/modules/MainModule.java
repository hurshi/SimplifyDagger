package io.github.hurshi.simplifydagger.modules;

import dagger.Module;
import dagger.Provides;
import io.github.hurshi.simplifydagger.scopes.AppScope;
import io.github.hurshi.simplifydagger.beans.Person;

@Module
public class MainModule {

    @AppScope
    @Provides
    Person providePerson() {
        return new Person("hurshi", 18);
    }
}
