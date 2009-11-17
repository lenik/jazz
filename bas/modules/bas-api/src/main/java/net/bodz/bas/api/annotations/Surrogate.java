package net.bodz.bas.api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Surrogate {

    boolean value() default true;

}
