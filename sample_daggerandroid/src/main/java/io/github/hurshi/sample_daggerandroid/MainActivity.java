package io.github.hurshi.sample_daggerandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.hurshi.sample_daggerandroid.beans.ActivityBean;
import io.github.hurshi.sample_daggerandroid.beans.AppBean;
import io.github.hurshi.sample_daggerandroid.modules.ActivityModule;
import io.github.hurshi.sample_daggerandroid.scopes.ActivityScope;
import io.github.hurshi.sample_daggerandroid.scopes.FragmentScope;
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent;


@AutoAndroidComponent(scope = ActivityScope.class, modules = {ActivityModule.class}, subScope = FragmentScope.class)
public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ActivityBean activityBean;

    @Inject
    AppBean appBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(">>>", "log from MainActivity  " + appBean.toString());
        Log.e(">>>", "log from MainActivity  " + activityBean.toString());

        loadFragment();
    }

    private void loadFragment() {
        TestFragment fragment = new TestFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, fragment);
        transaction.commit();
    }
}