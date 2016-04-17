package net.bodz.bas.meta.source;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CodingCost {

    /**
     * in minutes
     */
    int value();

    double price() default 0.0;

}
