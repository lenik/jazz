package net.bodz.bas.meta.codegen;

import java.lang.annotation.ElementType;
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
@IndexedType(includeAbstract = true)
// @Documented
// @Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IndexedType {

    String prefix() default "META-INF/services/";

    boolean includeAbstract() default false;

    boolean includeNonPublic() default false;

}
