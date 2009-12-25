package net.bodz.bas.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
public @interface ReflectField {

    /**
     * Field name.
     * 
     * It's recommended to set the name, for working with obfuscator.
     */
    String value() default "";

    Class<?> _class() default void.class;

    Class<?> type() default Object.class;

    /**
     * only bind to public accessible members
     */
    boolean secure() default false;

}
