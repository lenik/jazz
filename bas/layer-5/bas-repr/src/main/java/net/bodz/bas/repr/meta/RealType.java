package net.bodz.bas.repr.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.repr.path.builtin.ClassResourcePathDispatcher;

/**
 * TODO
 *
 * @see ClassResourcePathDispatcher
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RealType {

    Class<?> value();

}
