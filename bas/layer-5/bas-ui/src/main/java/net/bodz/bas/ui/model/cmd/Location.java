package net.bodz.bas.ui.model.cmd;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Location {

    Class<?>[] value();

}
