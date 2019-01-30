package io.github.hurshi.sample_daggerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.hurshi.sample_daggerandroid.beans.AppBean;
import io.github.hurshi.sample_daggerandroid.beans.MainActivityBean;
import io.github.hurshi.sample_daggerandroid.modules.MainActivityModule;
import io.github.hurshi.sample_daggerandroid.scopes.ActivityScope;
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent;


@AutoAndroidComponent(scope = ActivityScope.class, modules = {MainActivityModule.class}, fragments = MainFragment.class)
public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    MainActivityBean mainActivityBean;

    @Inject
    AppBean appBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(">>>", "log from MainActivity  " + appBean.toString());
        Log.e(">>>", "log from MainActivity  " + mainActivityBean.toString());

        loadFragment();
        clickToSecFragment();
    }

    private void loadFragment() {
        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, fragment);
        transaction.commit();
    }

    private void clickToSecFragment() {
        findViewById(R.id.txt).setOnClickListener(v -> {
            startActivity(new Intent(this, SecActivity.class));
        });
    }
}