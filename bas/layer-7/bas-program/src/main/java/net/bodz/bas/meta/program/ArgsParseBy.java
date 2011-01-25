package net.bodz.bas.meta.program;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.traits.IParser;

/** for method/callback only */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ArgsParseBy {

    Class<? extends IParser<?>>[] value() default {};

    /**
     * param[index] is applied to value[index], extra params are ignored.
     */
    String[] param() default {};

}
