package net.bodz.bas.repr.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Generic view tag.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Face {

    String[] value();

}
