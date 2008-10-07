package net.bodz.bas.gui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Enabled indicator for initial state only
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Enabled {

    boolean value() default true;

}
