package net.bodz.bas.util.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;

import net.bodz.bas.util.exception.RuntimizedException;

/**
 * If a method throws {@link RuntimizedException} (or {@link InvocationTargetException}), this
 * annotation indicates what the actual target exceptions are thrown.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target( { ElementType.METHOD, //
        ElementType.CONSTRUCTOR // Should be warning when declared on ctors
})
public @interface ExceptionCauses {

    Class<? extends Throwable>[] value();

}
