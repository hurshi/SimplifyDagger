package io.github.hurshi.simplifydagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoComponent {
    Class<?>[] modules() default {};

    Class<?> scope() default void.class;

    Class<?>[] dependencies() default {};
}