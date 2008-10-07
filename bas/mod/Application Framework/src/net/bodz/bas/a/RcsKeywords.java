package net.bodz.bas.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RcsKeywords(id = "$Id$", revision = "$Revision$")
public @interface RcsKeywords {

    String id() default "";

    String revision() default "";

    String author() default "";

    String state() default "";

}
