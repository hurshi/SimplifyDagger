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
import io.github.hurshi.simplifydagger.beans.Teacher;
import io.github.hurshi.simplifydagger.modules.PersonModule;
import io.github.hurshi.simplifydagger.modules.TeacherModule;
import io.github.hurshi.simplifydagger.scopes.ActivityScope;

@AutoComponent(modules = {PersonModule.class, TeacherModule.class}, scope = ActivityScope.class)
public class MainActivity extends AppCompatActivity {
    @Inject
    Person person;

    @Inject
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerAutoMainActivityComponent//在类上 @AutoComponent 就会自动生成该类
                .create()
                .inject(this);

        clickToSecActivity();

        Log.e(">>>", "person = " + new Gson().toJson(person));
        Log.e(">>>", "teacher = " + new Gson().toJson(teacher));
    }

    private void clickToSecActivity() {
        findViewById(R.id.toSec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KotlinActivity.class));
            }
        });
    }
}
