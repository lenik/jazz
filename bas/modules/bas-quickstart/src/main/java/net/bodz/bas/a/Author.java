package net.bodz.bas.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

    /** text or resource path */
    String[] value() default {};

}
