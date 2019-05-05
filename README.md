# SimplifyDagger

[![](https://jitpack.io/v/hurshi/SimplifyDagger.svg)](https://jitpack.io/#hurshi/SimplifyDagger)

### Feature

1. 简化 Dagger 使用，少写模版代码
2. 使用注解生成 Dagger 模版代码
3. 现有2个注解 @AutoComponent , @AutoAndroidComponent ,使用这2个注解已经能少写好几吨的 dagger 模版代码啦
4. 还有一个定制化程度较高的注解 @AutoViewModelFactoryComponent,是为[Android Architecture Component](https://developer.android.com/jetpack/docs/guide)设计的，主要是将`ViewModel`注入到 Dagger ,方便 Activity/Fragment 使用。关于Android Architecture Componnet 配合 Dagger2 的使用，可以看这个Sample:[Google Samples/GithubBrowserSample](https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample)

### Setup

1. add the JitPack respository in your build.gradle

   ```groovy
   allprojects {
   	repositories {
   		...
   		maven { url 'https://jitpack.io' }
   	}
   }
   ```

2. add dependencies in the build.gradle of the module:

   ```groovy
   dependencies {
   	kapt 'com.github.hurshi.SimplifyDagger:processor:0.09'  
   	implementation 'com.github.hurshi.SimplifyDagger:annotation:0.09'
   }
   ```


### Usage

##### @AutoComponent

* 功能：自动生成注入目标类的 Component 

* step1：在需要注入的目标类上添加注解 @AutoComponent(module = ..., scope = ....)

* setp2：注入。 `DaggerAutoTargetClassComponent.create().inject(this);`

  ```java
  // --------------------- MainActivity ------------------------------
  @AutoComponent(module = {MainModule.class}, scope = ActivityScope.class)//👈关注这行
  public class MainActivity extends AppCompatActivity {
    
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


##### @AutoAndroidComponent

* 功能：自动生成目标类 Dagger.Android 的 Component

* step1：在需要注入的目标类上添加注解 @AutoAndroidComponent

* step2：配置 Dagger.Android:（可能描述得有点抽象，不懂的直接看[示例代码](https://github.com/hurshi/SimplifyDagger/tree/master/sample_daggerandroid)）

  1. 在 AppComponent 的 modules 中添加 `AutoAndroidActivityScopeComponentInjector.class`
  2. 在 Activity 上使用 @AutoAndroidComponent 还可以添加 fragments, 用来指定 Activity 的 SubComponent, 方便 Fragment 使用 Activity 能用的 module

  ```java
  // --------------------- MainActivity -------------------------------
  @AutoAndroidComponent(scope = ActivityScope.class, modules = {MainActivityModule.class}, fragments = {MainFragment.class, MainFragment2.class})//👈关注这行，
  public class MainActivity extends AppCompatActivity {
      @Inject
      MainActivityBean mainActivityBean;
  
      @Inject
      AppBean appBean;
  
      @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          AndroidInjection.inject(this);
          Log.e(">>>", "log from MainActivity " + appBean.toString());
          Log.e(">>>", "log from MainActivity " + mainActivityBean.toString());
      }
  }
  
  // --------------------- AppComponent ------------------------------
  @AppScope
  @Component(modules = {AppModule.class, AndroidSupportInjectionModule.class, 
                        AutoAndroidActivityScopeComponentInjector.class})//👈关注这行，
  public interface AppComponent extends AndroidInjector<App> {
  }
  
  
  ```


### License

   ```
   Copyright 2019 Hurshi

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   ```

