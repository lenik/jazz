package net.bodz.bas.meta.codegen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcludedFromIndex {

    /**
     * Exclude recursively. By default, only the type annotated with {@link ExcludedFromIndex} is
     * exclude.
     */
    boolean recursive() default false;

}
