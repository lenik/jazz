package net.bodz.bas.cli.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BuildInfo {

    /**
     * Resource name, which contains counters
     */
    String value();

}
