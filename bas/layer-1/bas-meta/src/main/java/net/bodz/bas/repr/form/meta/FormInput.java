package net.bodz.bas.repr.form.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.repr.form.NullConvertion;

@Retention(RetentionPolicy.RUNTIME)
public @interface FormInput {

    String face() default "";

    String name() default "";

    boolean readOnly() default false;

    int textWidth() default 0;

    String inputMask() default "";

    String placeholder() default "";

    NullConvertion nullconv() default NullConvertion.EMPTY;

}
