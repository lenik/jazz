package net.bodz.dist.ins;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LogoImage {

    /**
     * resource-path
     */
    String value();

}
