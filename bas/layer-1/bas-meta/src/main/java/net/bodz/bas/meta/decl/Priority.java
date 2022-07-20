package net.bodz.bas.meta.decl;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @see net.bodz.bas.t.order.IPriority
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Priority {

    int URGENT = -100;
    int HIGH = -10;
    int DEFAULT = 0;
    int LOW = 10;
    int VERYLOW = 100;

    int value() default DEFAULT;

}
