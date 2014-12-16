package net.bodz.bas.meta.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.meta.decl.Redundant;

@Documented
@Redundant
@Retention(RetentionPolicy.RUNTIME)
public @interface Statistics {

    int timeout() default 0;

}
