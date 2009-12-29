package net.bodz.bas.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.PARAMETER })
public @interface UseSampleGenerator {

    Class<? extends ISampleGenerator<?>> value();

}
