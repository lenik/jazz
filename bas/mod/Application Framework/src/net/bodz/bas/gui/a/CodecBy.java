package net.bodz.bas.gui.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.gui.Codec;
import net.bodz.bas.lang.a.TargetSide;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@TargetSide(TargetSide.BEAN)
public @interface CodecBy {

    Class<? extends Codec> value() default Codec.class;

}
