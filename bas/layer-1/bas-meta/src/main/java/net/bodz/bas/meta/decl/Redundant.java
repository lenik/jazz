package net.bodz.bas.meta.decl;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

import net.bodz.bas.meta.meta.Implicit;

@Documented
@Implicit
@Inherited
public @interface Redundant {

    int rank() default 0;

}
