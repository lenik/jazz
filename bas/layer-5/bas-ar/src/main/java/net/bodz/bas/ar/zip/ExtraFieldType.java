package net.bodz.bas.ar.zip;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExtraFieldType {

    short id();

    boolean sizeTotal() default false;

// int size() default 0;
    boolean bigEndian() default false;

}
