package net.bodz.redist.installer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LogoImage {

    /**
     * resource-path
     */
    String value();

}
