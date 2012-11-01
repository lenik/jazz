package net.bodz.bas.potato.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Map;

import net.bodz.bas.model.AbstractDecorator;

public abstract class DecoratedAnnotatedElement
        extends AbstractDecorator<AnnotatedElement>
        implements IAnnotatedElement {

    private static final long serialVersionUID = 1L;

    public DecoratedAnnotatedElement(AnnotatedElement _orig) {
        super(_orig);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return getWrapped().isAnnotationPresent(annotationClass);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return getWrapped().getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return getWrapped().getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return getWrapped().getDeclaredAnnotations();
    }

    @Override
    public void findAnnotations(Map<Class<? extends Annotation>, Annotation> map) {
        AnnotatedElement wrapped = getWrapped();
        if (wrapped instanceof IAnnotatedElement) {
            IAnnotatedElement _wrapped = (IAnnotatedElement) wrapped;
            _wrapped.findAnnotations(map);
        } else {
            Annotation[] annotations = wrapped.getAnnotations();
            for (Annotation annotation : annotations)
                map.put(annotation.getClass(), annotation);
        }
    }

}
