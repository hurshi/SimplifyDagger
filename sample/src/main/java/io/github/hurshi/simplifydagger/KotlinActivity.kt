package io.github.hurshi.simplifydagger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import io.github.hurshi.simplifydagger.annotation.AutoComponent
import io.github.hurshi.simplifydagger.beans.Teacher
import io.github.hurshi.simplifydagger.modules.TeacherModule
import javax.inject.Inject

@AutoComponent(modules = [TeacherModule::class])
class KotlinActivity : AppCompatActivity() {
    @Inject
    lateinit var teacher: Teacher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAutoKotlinActivityComponent.create().inject(this)
        Log.e(">>>", "teacher = ${Gson().toJson(teacher)}")

        AutoComponent::class.java.canonicalName
    }
}