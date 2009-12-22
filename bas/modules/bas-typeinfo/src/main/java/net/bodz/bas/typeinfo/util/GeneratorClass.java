package net.bodz.bas.typeinfo.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.typeinfo.SampleGenerator;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.PARAMETER })
public @interface GeneratorClass {

    Class<? extends SampleGenerator<?>> value();

}
