package io.github.hurshi.simplifydagger;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @AppScope
    @Provides
    Person providePerson() {
        return new Person("hurshi", 18);
    }
}
