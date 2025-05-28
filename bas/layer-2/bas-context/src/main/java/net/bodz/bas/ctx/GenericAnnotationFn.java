package net.bodz.bas.ctx;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class GenericAnnotationFn {

    Set<Class<? extends Annotation>> concreteAnnotationClasses = new HashSet<Class<? extends Annotation>>();

    public GenericAnnotationFn(Class<? extends Annotation> genericAnnotationType) {
        for (Class<? extends Annotation> c : IndexedTypes.list(genericAnnotationType, true)) {
            Class<? extends Annotation> aClass = c;
            concreteAnnotationClasses.add(aClass);
        }
    }

    public Annotation getConcreteAnnotation(Class<?> c) {
        for (Annotation decl : c.getDeclaredAnnotations()) {
            for (Class<?> a : concreteAnnotationClasses)
                if (a.isInstance(decl))
                    return decl;
        }
        Class<?> superclass = c.getSuperclass();
        if (superclass == null)
            return null;
        return getConcreteAnnotation(superclass);
    }

}
