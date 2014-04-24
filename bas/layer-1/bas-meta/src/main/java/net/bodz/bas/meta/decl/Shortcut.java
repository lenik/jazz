package net.bodz.bas.meta.decl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Shortcut {

    String value() default "";

}
