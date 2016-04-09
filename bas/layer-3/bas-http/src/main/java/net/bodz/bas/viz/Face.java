package net.bodz.bas.viz;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Generic view tag.
 */
@Retention(RetentionPolicy.RUNTIME)
@ViewTag
public @interface Face {

    String[] value();

}
