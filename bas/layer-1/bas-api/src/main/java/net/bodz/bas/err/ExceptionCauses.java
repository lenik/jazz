package net.bodz.bas.err;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;

/**
 * If a method throws {@link RuntimizedException} (or {@link InvocationTargetException}), this
 * annotation indicates what the actual target exceptions are thrown.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.METHOD, //
        ElementType.CONSTRUCTOR // Should be warning when declared on ctors
})
public @interface ExceptionCauses {

    Class<? extends Throwable>[] value();

}
