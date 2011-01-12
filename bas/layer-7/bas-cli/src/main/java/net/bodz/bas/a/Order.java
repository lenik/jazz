package net.bodz.bas.a;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Order {

    int value() default 0;

    /** not used */
    String before() default "";

    /** not used */
    String after() default "";

}
