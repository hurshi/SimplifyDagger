package io.github.hurshi.sample_daggerandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.hurshi.sample_daggerandroid.beans.ActivityBean;
import io.github.hurshi.sample_daggerandroid.beans.AppBean;
import io.github.hurshi.sample_daggerandroid.modules.ActivityModule;
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent;
import io.github.hurshi.simplifydagger.scopes.ActivityScope;


@AutoAndroidComponent(scope = ActivityScope.class, modules = {ActivityModule.class})
public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ActivityBean activityBean;

    @Inject
    AppBean appBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(">>>", "MainActivity  " + appBean.toString());
        Log.e(">>>", "MainActivity  " + activityBean.toString());
    }
}
