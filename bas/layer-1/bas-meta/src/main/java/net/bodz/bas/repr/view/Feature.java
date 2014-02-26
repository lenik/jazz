package net.bodz.bas.repr.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Classification
@Retention(RetentionPolicy.RUNTIME)
public @interface Feature {

    String[] value();

}
