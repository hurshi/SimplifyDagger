package io.github.hurshi.sample_daggerandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.github.hurshi.sample_daggerandroid.modules.FragmentModule
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent
import io.github.hurshi.simplifydagger.scopes.FragmentScope


@AutoAndroidComponent(scope = FragmentScope::class, modules = [FragmentModule::class])
class TestFragment : DaggerFragment() {

//    @Inject
//    lateinit var fragmentBean: FragmentBean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        Log.e(">>>", "log from TestFragment  $fragmentBean")
    }
}