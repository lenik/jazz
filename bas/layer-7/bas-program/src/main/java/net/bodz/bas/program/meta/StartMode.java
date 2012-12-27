package net.bodz.bas.program.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StartMode {

    int CLI = 0;
    int GUI = 1;
    int DAEMON = 2;

    int value();

}
