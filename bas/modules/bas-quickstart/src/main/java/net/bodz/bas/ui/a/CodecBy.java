package net.bodz.bas.ui.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.commons.annotations.TargetSide;
import net.bodz.bas.commons.codec.Codec;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@TargetSide(TargetSide.BEAN)
public @interface CodecBy {

    @SuppressWarnings("unchecked")
    Class<? extends Codec> value() default Codec.class;

}
