package net.bodz.bas.api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
// @Target( { ElementType.METHOD })
public @interface TypeNote {

    Class<?>[] value();

}
