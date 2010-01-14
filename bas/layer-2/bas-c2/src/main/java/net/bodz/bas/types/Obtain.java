package net.bodz.bas.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotaton of How to obtain an instance of specified type.
 */
@Retention ( RetentionPolicy.RUNTIME)
@Target ( { ElementType.TYPE })
public @interface Obtain {

    /**
     * Can obtain by parsing a string.
     */
    Class<? extends IParser<?>> parser();

    /**
     * Can obtain by accessing a registry
     */
    Class<?> registry() default void.class;

}
