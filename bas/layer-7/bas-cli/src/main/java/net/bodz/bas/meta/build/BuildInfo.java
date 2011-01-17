package net.bodz.bas.meta.build;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BuildInfo {

    /**
     * resource name, which defines proeprties of counters
     */
    String value();

}
