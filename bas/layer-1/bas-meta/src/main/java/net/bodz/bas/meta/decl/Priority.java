package net.bodz.bas.meta.decl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @see net.bodz.bas.t.order.IPriority
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Priority {

    int value();

}
