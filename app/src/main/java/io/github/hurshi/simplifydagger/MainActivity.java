package io.github.hurshi.simplifydagger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.github.hurshi.simplifydagger.annotation.AutoComponent;
import io.github.hurshi.simplifydagger.beans.Person;
import io.github.hurshi.simplifydagger.modules.MainModule;
import io.github.hurshi.simplifydagger.scopes.AppScope;


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

        findViewById(R.id.toSec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecActivity.class));
            }
        });
    }
}
