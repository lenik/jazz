package net.bodz.bas.repr.form.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.repr.form.SpaceNormalization;

@Retention(RetentionPolicy.RUNTIME)
public @interface TextInput {

    int maxLength() default 0;

    char echoChar() default 0;

    SpaceNormalization space() default SpaceNormalization.TRIM;

    boolean multiLine() default false;

    boolean html() default false;

}
