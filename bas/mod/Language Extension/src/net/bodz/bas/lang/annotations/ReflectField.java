package net.bodz.bas.lang.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
public @interface ReflectField {

    Class<?> _class() default void.class;

    String value() default "";

    Class<?> type() default Object.class;

    /**
     * only bind to public accessible members
     */
    boolean secure() default false;

}
