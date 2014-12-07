package net.bodz.bas.meta.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * In a bean class, "internal" properties and methods should be excluded.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Internal {

}
