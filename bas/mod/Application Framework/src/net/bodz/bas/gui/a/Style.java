package net.bodz.bas.gui.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Style variation hint.
 * 
 * For example, a string may be in "label" or "edit" style, and a method may be
 * in "button" or "link" style.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Style {

    String value() default "";

}
