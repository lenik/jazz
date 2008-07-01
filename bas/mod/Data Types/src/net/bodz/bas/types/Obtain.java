package net.bodz.bas.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.cli.TypeParser;
import net.bodz.bas.cli.TypeParsers;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface Obtain {

    Class<? extends TypeParser<?>> parser() default TypeParsers.Void.class;

    Class<?> registry() default void.class;

}
