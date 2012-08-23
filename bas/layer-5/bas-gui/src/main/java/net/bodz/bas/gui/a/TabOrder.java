package net.bodz.bas.gui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Default using Order, if Order isn't specified, then 0 is used.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TabOrder {

    int value();

}
