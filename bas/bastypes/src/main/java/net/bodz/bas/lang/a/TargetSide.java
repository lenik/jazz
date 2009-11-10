package net.bodz.bas.lang.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target( { ElementType.ANNOTATION_TYPE })
public @interface TargetSide {

    int BEAN = 0;
    int GUI = 1;

    int value();

}
