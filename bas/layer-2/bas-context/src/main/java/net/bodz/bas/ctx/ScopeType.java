package net.bodz.bas.ctx;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;
import net.bodz.bas.meta.meta.Implicit;

/**
 * This annotation type is indexed.
 *
 * @see javax.inject.Scope
 */
@Documented
@Implicit
@Inherited
@IndexedType(publishDir = PublishDir.features, includeAnnotation = true)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ScopeType {

    GenericAnnotationFn fn = new GenericAnnotationFn(ScopeType.class);

}
