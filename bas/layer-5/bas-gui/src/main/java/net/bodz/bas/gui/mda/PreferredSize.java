package net.bodz.bas.gui.mda;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PreferredSize {

    /** width or columns */
    int width();

    /** height or rows */
    int height();

}
