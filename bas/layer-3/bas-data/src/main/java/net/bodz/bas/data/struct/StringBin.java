package net.bodz.bas.data.struct;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.io.LengthType;

@Retention(RetentionPolicy.RUNTIME)
public @interface StringBin {

    LengthType len();

    String encoding() default "utf-8";

}
