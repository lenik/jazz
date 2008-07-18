package net.bodz.bas.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

    /** text or resource path */
    String[] value() default {};

}
