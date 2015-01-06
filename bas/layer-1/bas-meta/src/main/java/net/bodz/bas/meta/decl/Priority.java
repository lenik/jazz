package net.bodz.bas.meta.decl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @see net.bodz.bas.t.order.IPriority
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Priority {

    int URGENT = -10;
    int HIGH = -5;
    int MEDIUM = 0;
    int LOW = 5;
    int VERYLOW = 10;

    int value();

}
