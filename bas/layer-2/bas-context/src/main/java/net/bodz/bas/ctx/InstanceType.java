package net.bodz.bas.ctx;

import java.lang.annotation.*;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;
import net.bodz.bas.meta.meta.Implicit;

/**
 * This annotation type is indexed.
 */
@Documented
@Implicit
@IndexedType(publishDir = PublishDir.features, includeAnnotation = true)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE })
public @interface InstanceType {

    class fn {

        static Set<Class<? extends Annotation>> scopeAnnotationClasses;
        static {
            scopeAnnotationClasses = new HashSet<>();
            for (Class<?> c : IndexedTypes.list(InstanceType.class, true)) {
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
