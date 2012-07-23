package net.bodz.bas.meta.build;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Number-only version annotation.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MainVersion {

    int[] value() default { 0 };

}
