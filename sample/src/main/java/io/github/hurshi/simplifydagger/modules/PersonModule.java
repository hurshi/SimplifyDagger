package io.github.hurshi.simplifydagger.modules;

import dagger.Module;
import dagger.Provides;
import io.github.hurshi.simplifydagger.beans.Person;
import io.github.hurshi.simplifydagger.scopes.ActivityScope;

@Module
public class PersonModule {

    @ActivityScope
    @Provides
    Person providePerson() {
        return new Person("person hurshi", 18);
    }
}
