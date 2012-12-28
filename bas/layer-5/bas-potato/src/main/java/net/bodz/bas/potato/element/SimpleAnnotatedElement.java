package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class SimpleAnnotatedElement
        implements IAnnotatedElement {

    IAnnotatedElement parent;
    Map<Class<? extends Annotation>, Annotation> declared;

    public SimpleAnnotatedElement() {
        declared = new HashMap<Class<? extends Annotation>, Annotation>();
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        if (declared.containsKey(annotationClass))
            return true;
        if (parent == null)
            return false;
        return parent.isAnnotationPresent(annotationClass);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        T annotation = (T) declared.get(annotationClass);
        if (annotation != null)
            return annotation;
        if (parent == null)
            return null;
        return parent.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        Map<Class<? extends Annotation>, Annotation> merged = new HashMap<>();
        findAnnotations(merged);
        Annotation[] array = merged.values().toArray(new Annotation[0]);
        return array;
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return declared.values().toArray(new Annotation[0]);
    }

    @Override
    public void findAnnotations(Map<Class<? extends Annotation>, Annotation> map) {
        if (parent != null)
            parent.findAnnotations(map);
        map.putAll(declared);
    }

}
