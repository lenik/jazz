package net.bodz.bas.lang.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Surrogate {

    boolean value() default true;

}
