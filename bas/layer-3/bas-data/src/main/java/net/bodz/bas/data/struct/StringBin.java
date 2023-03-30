package net.bodz.bas.data.struct;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.bodz.bas.io.LengthType;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface StringBin {

    int len() default -1;

    LengthType lenType() default LengthType.terminatedByNul;

    String encoding() default "utf-8";

}
