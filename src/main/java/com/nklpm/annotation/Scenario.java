package com.nklpm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Scenario {
    public String value() default "";
    public String description() default "";
    public String link() default "";
    public String[] steps() default "";
}
