# SimplifyDagger

简化 Dagger 使用

### AutoComponent

* 问题：每次都要写 component 模版代码，甚是麻烦

* 解决它：`@AutoComponent(module = ..., scope = ....)`

  ```java
  @AutoComponent(module = MainModule.class, scope = ActivityScope.class)
  public class MainActivity extends AppCompatActivity {
      @Inject
      Person person;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          DaggerAutoMainActivityComponent//在类上注解@AutoComponent会自动生成此类
              .create()
              .inject(this);
      }
  }

  ```

