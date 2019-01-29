package io.github.hurshi.sample_daggerandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.hurshi.sample_daggerandroid.beans.AppBean;
import io.github.hurshi.sample_daggerandroid.beans.Person;
import io.github.hurshi.sample_daggerandroid.modules.MainActivityModule;
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent;
import io.github.hurshi.simplifydagger.scopes.ActivityScope;


@AutoAndroidComponent(scope = ActivityScope.class, modules = {MainActivityModule.class})
public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    Person person;

    @Inject
    AppBean appBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(">>>", "person = " + new Gson().toJson(person));
        Log.e(">>>", "appBean = " + new Gson().toJson(appBean));

    }
}
