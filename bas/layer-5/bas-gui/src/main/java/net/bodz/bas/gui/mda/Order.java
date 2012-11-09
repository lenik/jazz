package net.bodz.bas.gui.mda;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Default order is 0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    int value();

}