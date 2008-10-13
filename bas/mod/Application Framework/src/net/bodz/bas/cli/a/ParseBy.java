package net.bodz.bas.cli.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.types.TypeParser;

/**
 * field or bean accessors
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
public @interface ParseBy {

    Class<? extends TypeParser> value() default TypeParser.class;

    String param() default "";

}
