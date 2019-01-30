# SimplifyDagger

使用 Dagger 的烦恼：每写一个 Activity/Fragment 都要写个 Component, 好麻烦呐。

SimplifyDagger 特性：简化 Dagger 使用，不再写模版代码

### @AutoComponent

* 特性：自动生成目标类的 Component 

* 2步走：

  1. 在需要注入的目标类上添加注解 @AutoComponent(module = ..., scope = ....)
  2. 注入。 `DaggerAutoTargetClassComponent.create().inject(this);`

  ```java
  @AutoComponent(module = {MainModule.class}, scope = ActivityScope.class)//👈关注这行
  public class MainActivity extends AppCompatActivity {
      @Inject
      Person mainActivityBean;
  
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


### @AutoAndroidComponent

* 特性：自动生成目标类 Dagger.Android 的 Component

* 使用方式和 @AutoComponent 相似，

  1. 在需要注入的目标类上添加注解 @AutoAndroidComponent
  2. 配置 Dagger.Android:（可能描述得有点抽象，不懂的直接看[示例代码](https://github.com/hurshi/SimplifyDagger/tree/master/sample_daggerandroid)）
     1. 在 AppComponent 的 modules 中添加 `AutoAndroidActivityScopeComponentInjector.class`
     2. 在 Activity 上使用 @AutoAndroidComponent 还可以添加 fragments, 用来指定 Activity 的 SubComponent, 方便 Fragment 使用 Activity 能用的 module
  3. 之后和平时一样使用就行

  ```java
  // --------------------- AppComponent ------------------------------
  @AppScope
  @Component(modules = {AppModule.class, AndroidSupportInjectionModule.class, AutoAndroidActivityScopeComponentInjector.class})
  public interface AppComponent extends AndroidInjector<App> {
  }
  
  // --------------------- MainActivity -------------------------------
  @AutoAndroidComponent(scope = ActivityScope.class, modules = {MainActivityModule.class}, fragments = {MainFragment.class, MainFragment2.class})
  public class MainActivity extends AppCompatActivity {
      @Inject
      MainActivityBean mainActivityBean;
  
      @Inject
      AppBean appBean;
  
      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          AndroidInjection.inject(this);
          Log.e(">>>", "log from MainActivity 👉 " + appBean.toString());
          Log.e(">>>", "log from MainActivity 👉 " + mainActivityBean.toString());
      }
  }
  ```

  