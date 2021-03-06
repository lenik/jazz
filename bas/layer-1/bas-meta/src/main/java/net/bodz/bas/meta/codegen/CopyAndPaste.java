package net.bodz.bas.meta.codegen;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Don't modify derived types and members by hand. because they are derived from existing models.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface CopyAndPaste {

    /**
     * Original model type
     */
    Class<?> value() default void.class;

    String method() default "";

}
