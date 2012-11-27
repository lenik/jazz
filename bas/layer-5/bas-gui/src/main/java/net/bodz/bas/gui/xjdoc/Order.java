package net.bodz.bas.gui.xjdoc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Default order is 0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    int value();

}
