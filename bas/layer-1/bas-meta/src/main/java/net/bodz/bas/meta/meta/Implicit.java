package net.bodz.bas.meta.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If <code>&#64;Foo</code> is implicit and <code>&#64;Bar</code> is annotated with
 * <code>&#64;Foo</code>, then <code>&#64;Foo</code> is implicit annotated when
 * <code>&#64;Bar</code> is used.
 *
 * {@link Implicit} is generally used with {@link Inherited}.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Implicit {

}
