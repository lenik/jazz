package net.bodz.bas.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Icon {

    /**
     * [usage|] resource-path
     * 
     * using class loader of the declaring class
     * 
     * Examples:
     * <ul>
     * <li>"org.example.resources.HelloIcon"
     * <li>"big|org.example.resources.BigIcon"
     * <li>"disabled|org.example.resources.DisabledIcon"
     * </ul>
     */
    String[] value() default {};

}
