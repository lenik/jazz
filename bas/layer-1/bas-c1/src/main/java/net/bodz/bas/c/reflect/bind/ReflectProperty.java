package net.bodz.bas.c.reflect.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
public @interface ReflectProperty {

    Class<?> _class() default void.class;

    String value() default "";

    boolean secure() default false;

}
