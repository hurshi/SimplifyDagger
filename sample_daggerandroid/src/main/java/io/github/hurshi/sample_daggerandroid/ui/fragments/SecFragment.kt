package io.github.hurshi.sample_daggerandroid.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.github.hurshi.sample_daggerandroid.R
import io.github.hurshi.sample_daggerandroid.beans.AppBean
import io.github.hurshi.sample_daggerandroid.beans.SecActivityBean
import io.github.hurshi.sample_daggerandroid.beans.SecFragmentBean
import io.github.hurshi.sample_daggerandroid.modules.SecFragmentModule
import io.github.hurshi.sample_daggerandroid.scopes.FragmentScope
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent
import javax.inject.Inject


@AutoAndroidComponent(scope = FragmentScope::class, modules = [SecFragmentModule::class])
class SecFragment : DaggerFragment() {

    @Inject
    lateinit var secFragmentBean: SecFragmentBean

    @Inject
    lateinit var secActivityBean: SecActivityBean

    @Inject
    lateinit var appBean: AppBean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sec, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(">>>", "log from SecFragment 👉 $appBean")
        Log.e(">>>", "log from SecFragment 👉 $secActivityBean")
        Log.e(">>>", "log from SecFragment 👉 $secFragmentBean")
    }
}