package net.bodz.bas.c.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

class NullAnnotatedElement
        implements AnnotatedElement {

    static final Annotation[] EMPTY = {};

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return false;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return null;
    }

    @Override
    public Annotation[] getAnnotations() {
        return EMPTY;
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return EMPTY;
    }

}
