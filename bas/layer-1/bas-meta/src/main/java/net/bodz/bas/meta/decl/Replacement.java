package net.bodz.bas.meta.decl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;

@Documented
@IndexedType(publishDir = PublishDir.features, includeAbstract = true, includeNonPublic = true, includeAnnotation = true)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Replacement {

    Class<?> value() default superclass.class;

    class superclass {
    }

}
