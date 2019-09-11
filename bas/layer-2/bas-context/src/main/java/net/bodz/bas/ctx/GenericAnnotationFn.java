package net.bodz.bas.ctx;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.type.IndexedTypes;

public class GenericAnnotationFn {

    Set<Class<? extends Annotation>> concreteAnnotationClasses = new HashSet<Class<? extends Annotation>>();

    public GenericAnnotationFn(Class<? extends Annotation> genericAnnotationType) {
        for (Class<?> c : IndexedTypes.list(genericAnnotationType, true)) {
            @SuppressWarnings("unchecked")
            Class<? extends Annotation> aClass = (Class<? extends Annotation>) c;
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
