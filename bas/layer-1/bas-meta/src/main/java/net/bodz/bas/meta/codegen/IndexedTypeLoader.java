package net.bodz.bas.meta.codegen;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ExcludedFromIndex
@Retention(RetentionPolicy.RUNTIME)
public @interface IndexedTypeLoader {

    /** The indexed. */
    Class<?>[] value() default {};

}
