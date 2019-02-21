package io.github.hurshi.simplifydagger.annotation;

import android.arch.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface AutoViewModelFactoryComponent {
    Class<? extends ViewModel> viewModel() default ViewModel.class;

    Class<?> factoryScope() default void.class;

    Class<?> factory() default void.class;

    boolean tryMerge() default true;
}