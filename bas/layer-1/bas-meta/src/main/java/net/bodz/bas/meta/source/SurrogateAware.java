package net.bodz.bas.meta.source;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface SurrogateAware {

    boolean value() default true;

}
