package io.github.hurshi.sample_architecture_components

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import io.github.hurshi.sample_architecture_components.entity.DaoSessionSimulate
import io.github.hurshi.sample_architecture_components.scopes.ActivityScope
import io.github.hurshi.sample_architecture_components.scopes.AppScope
import io.github.hurshi.sample_architecture_components.vm.MainActivityViewModel
import io.github.hurshi.sample_architecture_components.vm_factory.VMFactory
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent
import io.github.hurshi.simplifydagger.annotation.AutoViewModelFactoryComponent
import javax.inject.Inject

@AutoViewModelFactoryComponent(factory = VMFactory::class, viewModel = MainActivityViewModel::class, factoryScope = AppScope::class)
@AutoAndroidComponent(scope = ActivityScope::class)
class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var daoSessionSimulate: DaoSessionSimulate

    private val vm: MainActivityViewModel by lazy {
        ViewModelProviders.of(this@MainActivity, factory).get(MainActivityViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(">>>", "daoSessionSimulate = $daoSessionSimulate")
        Log.e(">>>", "daoSessionSimulate from vm = ${vm.getDaoSessionSimulate()}")
    }
}