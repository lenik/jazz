package net.bodz.bas.c.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for test purpose.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface XyzHint {

    /** text or resource path */
    String[] value() default {};

}
