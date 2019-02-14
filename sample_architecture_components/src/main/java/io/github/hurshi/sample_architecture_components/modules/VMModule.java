package io.github.hurshi.sample_architecture_components.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.github.hurshi.sample_architecture_components.vm.MainActivityViewModel;
import io.github.hurshi.sample_architecture_components.vm_factory.VMFactory;
import io.github.hurshi.simplifydagger.annotation.ViewModelKey;

@Module
public abstract class VMModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel bindMainActivityModule(MainActivityViewModel mm);

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(VMFactory factory);


}
