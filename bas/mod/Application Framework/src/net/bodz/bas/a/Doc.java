package net.bodz.bas.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Doc {

    /**
     * text
     * 
     * /resource-path
     * 
     * #bundle.property
     */
    String[] value() default {};

}
