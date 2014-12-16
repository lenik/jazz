package net.bodz.bas.meta.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.meta.decl.Redundant;

@Documented
@Redundant
@Retention(RetentionPolicy.RUNTIME)
public @interface Derived {

    boolean cached() default false;

    int SECOND = 1;
    int MINUTE = SECOND * 60;
    int HOUR = MINUTE * 60;
    int DAY = HOUR * 24;
    int WEEK = DAY * 7;
    int MONTH = DAY * 30;
    int YEAR = DAY * 365;

    /**
     * 0 if not cached.
     */
    int cacheUpdateInterval() default 0;

}
