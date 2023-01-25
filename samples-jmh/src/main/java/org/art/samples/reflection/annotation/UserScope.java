package org.art.samples.reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {

    String value();

    boolean required();

    Class<? extends Number>[] numberClasses();
}
