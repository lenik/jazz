package net.bodz.bas.gui.mda;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Border {

    /**
     * Border Width
     */
    int value() default 1;

}
