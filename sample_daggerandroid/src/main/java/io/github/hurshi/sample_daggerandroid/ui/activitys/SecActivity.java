package io.github.hurshi.sample_daggerandroid.ui.activitys;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.hurshi.sample_daggerandroid.R;
import io.github.hurshi.sample_daggerandroid.beans.AppBean;
import io.github.hurshi.sample_daggerandroid.beans.SecActivityBean;
import io.github.hurshi.sample_daggerandroid.modules.SecActivityModule;
import io.github.hurshi.sample_daggerandroid.scopes.ActivityScope;
import io.github.hurshi.sample_daggerandroid.ui.fragments.SecFragment;
import io.github.hurshi.simplifydagger.annotation.AutoAndroidComponent;


@AutoAndroidComponent(scope = ActivityScope.class, modules = {SecActivityModule.class}, fragments = SecFragment.class)
public class SecActivity extends DaggerAppCompatActivity {
    @Inject
    SecActivityBean secActivityBean;

    @Inject
    AppBean appBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        Log.e(">>>", "log from SecActivity 👉 " + appBean.toString());
        Log.e(">>>", "log from SecActivity 👉 " + secActivityBean.toString());

        loadFragment();
    }

    private void loadFragment() {
        SecFragment fragment = new SecFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, fragment);
        transaction.commit();
    }
}