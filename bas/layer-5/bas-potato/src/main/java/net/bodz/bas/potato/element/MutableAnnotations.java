package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;

public class MutableAnnotations
        implements IAnnotated {

    private IAnnotated parent;
    private Map<Class<?>, Annotation> declaredMap;

    public MutableAnnotations() {
        this(null);
    }

    public MutableAnnotations(IAnnotated parent) {
        this.parent = parent;
        this.declaredMap = new HashMap<Class<?>, Annotation>();
    }

    public IAnnotated getParent() {
        return parent;
    }

    public void setParent(IAnnotated parent) {
        this.parent = parent;
    }

    public void declare(Class<? extends Annotation> annotationType, Annotation annotation) {
        if (annotationType == null)
            throw new NullPointerException("annotationType");
        if (annotation == null)
            throw new NullPointerException("annotation");
        declaredMap.put(annotationType, annotation);
    }

    public void remove(Class<?> annotationType) {
        declaredMap.remove(annotationType);
    }

    /** ⇱ Implementation Of {@link AnnotatedElement}. */
    /* _____________________________ */static section.iface __ANNOTATED_ELEMENT__;

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        if (declaredMap.containsKey(annotationClass))
            return true;
        if (parent != null)
            return parent.isAnnotationPresent(annotationClass);
        else
            return false;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        T annotation = (T) declaredMap.get(annotationClass);
        if (annotation != null)
            return annotation;
        if (parent != null)
            return parent.getAnnotation(annotationClass);
        else
            return null;
    }

    @Override
    public Annotation[] getAnnotations() {
        Map<Class<?>, Annotation> all = new HashMap<>();
        dumpAnnotations(all);
        Annotation[] array = all.values().toArray(new Annotation[0]);
        return array;
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return declaredMap.values().toArray(new Annotation[0]);
    }

    /** ⇱ Implementation Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __I_ANNOTATED__;

    @Override
    public Map<Class<?>, Annotation> getAnnotationMap() {
        Map<Class<?>, Annotation> all = new HashMap<>();
        dumpAnnotations(all);
        return all;
    }

    @Override
    public Map<Class<?>, Annotation> getDeclaredAnnotationMap() {
        return declaredMap;
    }

    @Override
    public void dumpAnnotations(Map<Class<?>, Annotation> map) {
        if (parent != null)
            parent.dumpAnnotations(map);
        map.putAll(declaredMap);
    }

}
