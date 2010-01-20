package net.bodz.bas.cli.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** for method/callback only */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface ArgsParseBy {

    Class<? extends TypeParser>[] value() default {};

    /**
     * param[index] is applied to value[index], extra params are ignored.
     */
    String[] param() default {};

}
