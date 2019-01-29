# SimplifyDagger

简化 Dagger 使用，不再写模版代码

### AutoComponent

* 烦恼：不要每注入一个类都要写个 component, 每个 component 长得都差不多，能不能自动给我生成啊？

* 解决它需2步：

  1. 在需要注入的类上添加注解 @AutoComponent(module = ..., scope = ....)`
  2. 在合适的地方注入：`DaggerAuto***Component...inject(this)...`, 这里的`DaggerAuto***Component`是自动生成的，开发者不用再关注。

  ```java
  @AutoComponent(module = {MainModule.class}, scope = ActivityScope.class)//👈关注这行
  public class MainActivity extends AppCompatActivity {
      @Inject
      Person activityBean;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          //DaggerAutoMainActivityComponent在由注解@AutoComponent自动生成
          DaggerAutoMainActivityComponent//👈关注这行，
              .create()
              .inject(this);
      }
  }
  
  ```


### AutoAndroidComponent

* 配合 Dagger.Android 使用的自动注入

* 使用方式和 @AutoComponent 相似，

  1. 在需要注入的类上添加注解 @AutoAndroidComponent
  2. **这个只需配置一次就好：**在 AppComponent 的 modules 中添加 `io.github.hurshi.simplifydagger.AutoAndroidComponentInjector::class`
  3. 之后和平时一样使用就行

  ```java
  @AppScope
  @Component(modules = [ AndroidSupportInjectionModule::class,
      io.github.hurshi.simplifydagger.AutoAndroidComponentInjector::class])
  interface AppComponent : AndroidInjector<App>
  
  @AutoAndroidComponent(scope = ActivityScope.class, modules = {MainModule.class})
  public class MainActivity extends AppCompatActivity {
      @Inject
      Person activityBean;
  
      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          AndroidInjection.inject(this);
          Log.e(">>>", "activityBean = " + new Gson().toJson(activityBean));
      }
  }
  ```

  