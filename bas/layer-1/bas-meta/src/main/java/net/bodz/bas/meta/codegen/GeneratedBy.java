package net.bodz.bas.meta.codegen;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface GeneratedBy {

    Class<?> value();

}
