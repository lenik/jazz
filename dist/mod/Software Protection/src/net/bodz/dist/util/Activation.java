package net.bodz.dist.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Activation {

    String prefix();

    int segments();

}
