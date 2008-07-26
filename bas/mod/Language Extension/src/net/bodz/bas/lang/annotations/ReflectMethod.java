package net.bodz.bas.lang.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
public @interface ReflectMethod {

    Class<?> _class() default void.class;

    String value() default "";

    Class<?>[] parameters() default {};

    boolean secure() default false;

}
