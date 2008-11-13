package net.bodz.bas.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.loader.LoadConfig;

/**
 * load in order of:
 * <ol>
 * <li>{@link #value()} libraries to add
 * <li>{@link #types()} types to initialize
 * <li>{@link #eval()} expressions to eval
 * </ol>
 * 
 * @see LoadConfig#load(int)
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoadDelayed {

    String[] value() default {};

    Class<?>[] types() default {};

    String[] eval() default {};

}
