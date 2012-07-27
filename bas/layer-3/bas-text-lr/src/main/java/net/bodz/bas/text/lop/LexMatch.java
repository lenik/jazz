package net.bodz.bas.text.lop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.lang.ControlReturn;

/**
 * The {@link Class#getDeclaredFields()} and {@link Class#getDeclaredMethods()} must return the
 * members in the order same as their declaration.
 * 
 * For static fields, this annotation defines a const regexp.
 * 
 * For methods, this annotation defines a rule action.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface LexMatch {

    /**
     * regular expression
     */
    String value() default "";

    /**
     * specify the order when necessary.
     */
    int order() default 0;

    /**
     * for exclusive states, the name shall start with '-'
     */
    String state() default "";

    /**
     * for <code>void</code> rule(), true if it may throw {@link ControlReturn}.
     */
    boolean mayReturn() default false;

}
