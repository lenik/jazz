package net.bodz.bas.gui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.types.ValueCheck;

/**
 * Bean Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD })
public @interface UIFormat {

    Class<?> preferredDisplayType() default Object.class;

    Class<? extends UICodec> value() default UICodec.class;

    Class<? extends ValueCheck> check() default ValueCheck.class;

}
