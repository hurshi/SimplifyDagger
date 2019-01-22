package io.github.hurshi.simplifydagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.hurshi.simplifydagger.annotation.AutoComponent;


@AutoComponent()
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
