package net.bodz.bas.meta.program;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
public @interface OptionGroup {

    /**
     * The doc text of the group should be put somewhere else, to avoid the duplicated doc texts
     * here.
     */
    String value();

    /**
     * The order of the groups, the smaller rank number will be shown in the first.
     */
    int rank() default 0;

}
