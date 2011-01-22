package net.bodz.bas.ui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Color {

    /**
     * [NAME]#AARRGGBB
     * 
     * if color name exists, then ignore the color value
     */
    String value() default "";

    /**
     * Background Color
     */
    String back() default "";

}
