package net.bodz.bas.commons.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Don't modify derived types and members by hand. because they are derived from existing models.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Derived {

    /**
     * Original model type
     */
    Class<?> value() default void.class;

}
