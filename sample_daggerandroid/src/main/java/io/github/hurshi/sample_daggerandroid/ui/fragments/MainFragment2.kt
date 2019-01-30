package io.github.hurshi.sample_daggerandroid.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.github.hurshi.sample_daggerandroid.R
import io.github.hurshi.sample_daggerandroid.beans.AppBean
import io.github.hurshi.sample_daggerandroid.beans.MainActivityBean
import io.github.hurshi.sample_daggerandroid.beans.MainFragmentBean
import io.github.hurshi.sample_daggerandroid.modules.MainFragmentModule
import io.github.hurshi.sample_daggerandroid.scopes.FragmentScope
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent
import javax.inject.Inject


@AutoAndroidComponent(scope = FragmentScope::class, modules = [MainFragmentModule::class])
class MainFragment2 : DaggerFragment() {

    @Inject
    lateinit var mainFragmentBean: MainFragmentBean

    @Inject
    lateinit var mainActivityBean: MainActivityBean

    @Inject
    lateinit var appBean: AppBean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(">>>", "log from MainFragment2 👉 $appBean")
        Log.e(">>>", "log from MainFragment2 👉 $mainActivityBean")
        Log.e(">>>", "log from MainFragment2 👉 $mainFragmentBean")
    }
}