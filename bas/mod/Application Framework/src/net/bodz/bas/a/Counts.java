package net.bodz.bas.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Counts {

    /**
     * resource name, which defines proeprties of counters
     */
    String value();

}
