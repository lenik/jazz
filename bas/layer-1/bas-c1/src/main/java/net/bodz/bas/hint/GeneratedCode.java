package net.bodz.bas.hint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Don't modify derived types and members by hand. because they are derived from existing models.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface GeneratedCode {

    /**
     * Original model type
     */
    Class<?> value() default void.class;

}
