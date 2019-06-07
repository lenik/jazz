package net.bodz.bas.repr.form.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NumericInput {

    double min() default Double.MIN_VALUE;

    double max() default Double.MAX_VALUE;

    double step() default Double.NaN;

    String numberFormat() default "";

}
