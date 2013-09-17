package net.bodz.pkg.obfuz.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Activation {

    String productId() default "";

    String prefix();

    int segments();

}
