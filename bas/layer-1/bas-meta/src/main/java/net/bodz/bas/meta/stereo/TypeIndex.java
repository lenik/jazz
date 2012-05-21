package net.bodz.bas.meta.stereo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to scan service classes and generate the index file.
 * 
 * The annotation is inheritable, so only need to annotate on the base type.
 * 
 * The result index file is <code>META-INF/services/&lt;base-type&gt;</code> by default.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TypeIndex {

    String section() default "services";

    boolean includeAbstract() default false;

    boolean includeNonPublic() default false;

}
