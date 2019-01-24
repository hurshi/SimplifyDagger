package io.github.hurshi.simplifydagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.github.hurshi.simplifydagger.annotation.AutoComponent;


@AutoComponent(module = MainModule.class, scope = AppScope.class)
public class MainActivity extends AppCompatActivity {
    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerAutoMainActivityComponent.create().inject(this);
        Log.e(">>>", "person = " + new Gson().toJson(person));
    }
}
