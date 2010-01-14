package net.bodz.bas.c1.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Doc {

    /**
     * Can be:
     * <ul>
     * <li>text
     * <li>/resource-path
     * <li>#bundle.property
     * </ul>
     */
    String[] value() default {};

}
