package net.bodz.bas.meta.decl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface ThreadUnsafe {

    /**
     * the synchronization shall applied by groups.
     * 
     * if not specified, the scope is `all fields of the declaring-type'.
     * <p>
     * the group may defined as private int in the declaring type.
     */
    int scope() default -1;

    /**
     * which fields are not synchronized accessed.
     * <p>
     * if not specified, may be all fields are accessed.
     */
    String[] fields() default {};

}
