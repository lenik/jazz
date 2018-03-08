package net.bodz.bas.ctx.scope;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;
import net.bodz.bas.meta.meta.MetaAnnotation;

/**
 * This annotation type is indexed.
 *
 * @see javax.inject.Scope
 */
@Documented
@IndexedType(publishDir = PublishDir.features, includeClass = false, includeAnnotation = true)
@MetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface _Scope {

    class fn {

        static Set<Class<? extends Annotation>> scopeAnnotationClasses;
        static {
            scopeAnnotationClasses = new HashSet<>();
            for (Class<?> c : IndexedTypes.list(_Scope.class, true)) {
                @SuppressWarnings("unchecked")
                Class<? extends Annotation> aClass = (Class<? extends Annotation>) c;
                scopeAnnotationClasses.add(aClass);
            }
        }

        public static Annotation getScopeAnnotation(Class<?> c) {
            for (Annotation decl : c.getDeclaredAnnotations()) {
                for (Class<?> a : scopeAnnotationClasses)
                    if (a.isInstance(decl))
                        return decl;
            }
            Class<?> superclass = c.getSuperclass();
            if (superclass == null)
                return null;
            return getScopeAnnotation(superclass);
        }
    }

}
