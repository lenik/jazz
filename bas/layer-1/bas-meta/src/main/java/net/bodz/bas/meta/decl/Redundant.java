package net.bodz.bas.meta.decl;

import java.lang.annotation.Documented;

import net.bodz.bas.meta.meta.MetaAnnotation;

@Documented
@MetaAnnotation
public @interface Redundant {

    int rank() default 0;

}
