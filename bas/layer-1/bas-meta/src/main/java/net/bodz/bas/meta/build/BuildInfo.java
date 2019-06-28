package net.bodz.bas.meta.build;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BuildInfo {

    /**
     * Resource name, which contains counters
     */
    String value();

}
