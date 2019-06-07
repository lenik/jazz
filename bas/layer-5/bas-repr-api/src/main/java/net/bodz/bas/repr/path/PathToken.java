package net.bodz.bas.repr.path;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PathToken {

    /**
     * An empty token name is used to union the namespace with the parent.
     */
    String[] value();

}
