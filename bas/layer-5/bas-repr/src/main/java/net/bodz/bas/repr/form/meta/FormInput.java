package net.bodz.bas.repr.form.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.repr.form.NullConvertion;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.repr.form.SpaceNormalization;

@Retention(RetentionPolicy.RUNTIME)
public @interface FormInput {

    SortOrder sort() default SortOrder.NONE;

    boolean readOnly() default false;

    int maxLength() default 0;

    int textWidth() default 0;

    char echoChar() default 0;

    String placeholder() default "";

    String numberFormat() default "";

    NullConvertion nullconv() default NullConvertion.NONE;

    SpaceNormalization space() default SpaceNormalization.NONE;

}
