package net.bodz.bas.hint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * because java has no `const' keywords.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Const {

}
