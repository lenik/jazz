package net.bodz.bas.meta.lang;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// @Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeParameter {

    Class<?> c() default void.class;

    String id() default "";

    String value() default "";

}
