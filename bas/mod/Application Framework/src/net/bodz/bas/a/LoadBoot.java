package net.bodz.bas.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.cli.util.Conditions;

/**
 * @see BootInfo
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadBoot {

    /**
     * When the loader is specified, the libs are put under CLASSPATH_USER, and
     * the loader must add the user classpath to its find path.
     */
    Class<? extends ClassLoader> loader() default ClassLoader.class;

    /**
     * [type-condition|] library-spec
     * 
     * if type-condition isn't specified, 'fortype %libspec' is used.
     * 
     * @see LoadInfo#findLib(String)
     * @see Conditions#fortype(String)
     */
    String[] value() default {};

}
