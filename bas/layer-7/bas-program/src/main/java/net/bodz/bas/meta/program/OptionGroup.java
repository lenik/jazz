package net.bodz.bas.meta.program;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
public @interface OptionGroup {

    /**
     * The option group class.
     */
    Class<?> value();

    /**
     * The order of the groups, the smaller rank number will be shown in the first.
     */
    int order() default 0;

}
