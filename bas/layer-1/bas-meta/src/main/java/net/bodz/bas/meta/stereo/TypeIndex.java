package net.bodz.bas.meta.stereo;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeIndex {

    String section() default "services";

    boolean includeAbstract() default false;

    boolean includeNonPublic() default false;

}
