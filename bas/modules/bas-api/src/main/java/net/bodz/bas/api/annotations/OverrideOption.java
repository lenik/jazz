package net.bodz.bas.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target( { ElementType.METHOD, ElementType.CONSTRUCTOR })
public @interface OverrideOption {

    /**
     * Should the overrided method be chained
     */
    ChainUsage chain() default ChainUsage.OPTIONAL;

    /**
     * The preferred chaining call order.
     */
    ChainOrder order() default ChainOrder.ANY;

    /**
     * Override only one method in the same group.
     */
    String group() default "";

}
