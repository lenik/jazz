package net.bodz.bas.repr.form.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IndexColumn {

    /**
     * Whether show or not.
     */
    boolean display() default true;

    /**
     * The default visibility.
     */
    boolean visible() default true;

    /**
     * -1 if not specified.
     */
    int maxLength() default -1;

}
