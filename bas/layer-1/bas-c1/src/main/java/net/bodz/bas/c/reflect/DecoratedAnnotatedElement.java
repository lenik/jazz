package net.bodz.bas.c.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedAnnotatedElement
        extends AbstractDecorator<AnnotatedElement>
        implements AnnotatedElement {

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

}
