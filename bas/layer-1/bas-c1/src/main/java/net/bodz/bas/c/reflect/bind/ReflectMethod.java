package net.bodz.bas.c.reflect.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ReflectMethod {

    /**
     * Method name.
     * 
     * It's recommended to set the name, for working with obfuscator.
     */
    String value() default "";

    Class<?> _class() default void.class;

    Class<?>[] parameters() default {};

    boolean secure() default false;

}
