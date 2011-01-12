package net.bodz.bas.meta.info;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

    /** text or resource path */
    String[] value() default {};

}
