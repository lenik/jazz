package net.bodz.bas.lang.a;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * because java has no `const' keywords.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Const {

    ConstTarget[] value() default { ConstTarget.VARIABLE };

}
