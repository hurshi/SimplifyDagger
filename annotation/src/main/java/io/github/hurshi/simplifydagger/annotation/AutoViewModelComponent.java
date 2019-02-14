package io.github.hurshi.simplifydagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface AutoViewModelComponent {
    Class<?> viewModel() default void.class;

    Class<?> scope() default void.class;

    Class<?> factory() default void.class;

    boolean tryMerge() default true;
}