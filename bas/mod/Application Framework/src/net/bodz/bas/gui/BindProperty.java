package net.bodz.bas.gui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * GUI Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD })
public @interface BindProperty {

    /**
     * name of property in bean class to bind with.
     * 
     * indirect field access using dot(.)
     */
    String value();

    int UPDATE  = 1;
    int REFRESH = 2;

    int mode() default UPDATE;

}
