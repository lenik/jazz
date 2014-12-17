package net.bodz.bas.meta.decl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.ANNOTATION_TYPE)
public @interface Redundant {

    int rank() default 0;

}
