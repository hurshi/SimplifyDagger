package io.github.hurshi.sample_daggerandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.github.hurshi.sample_daggerandroid.beans.ActivityBean
import io.github.hurshi.sample_daggerandroid.beans.AppBean
import io.github.hurshi.sample_daggerandroid.beans.FragmentBean
import io.github.hurshi.sample_daggerandroid.modules.FragmentModule
import io.github.hurshi.sample_daggerandroid.scopes.FragmentScope
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent
import javax.inject.Inject


@AutoAndroidComponent(scope = FragmentScope::class, modules = [FragmentModule::class])
class TestFragment : DaggerFragment() {

    @Inject
    lateinit var fragmentBean: FragmentBean

    @Inject
    lateinit var activityBean: ActivityBean

    @Inject
    lateinit var appBean: AppBean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(">>>", "log from TestFragment  $appBean")
        Log.e(">>>", "log from TestFragment  $activityBean")
        Log.e(">>>", "log from TestFragment  $fragmentBean")
    }
}