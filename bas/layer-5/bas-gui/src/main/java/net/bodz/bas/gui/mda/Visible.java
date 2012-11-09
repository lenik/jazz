package net.bodz.bas.gui.mda;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Visible indicator for initial state only
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Visible {

    boolean value() default true;

}