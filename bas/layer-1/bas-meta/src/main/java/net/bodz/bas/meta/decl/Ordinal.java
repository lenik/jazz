package net.bodz.bas.meta.decl;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Default order is 0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Ordinal {

    int value() default 0;

}
