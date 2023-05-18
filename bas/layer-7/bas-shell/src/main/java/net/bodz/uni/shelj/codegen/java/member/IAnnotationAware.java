package net.bodz.uni.shelj.codegen.java.member;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public interface IAnnotationAware
        extends
            AnnotatedElement {

    AnnotatedElement toAnnotatedElement();

    @Override
    default boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        AnnotatedElement ae = toAnnotatedElement();
        if (ae == null)
            return false;
        else
            return ae.isAnnotationPresent(annotationClass);
    }

    @Override
    default <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        AnnotatedElement ae = toAnnotatedElement();
        if (ae == null)
            return null;
        else
            return ae.getAnnotation(annotationClass);
    }

    @Override
    default Annotation[] getAnnotations() {
        AnnotatedElement ae = toAnnotatedElement();
        if (ae == null)
            return new Annotation[0];
        else
            return ae.getAnnotations();
    }

    @SuppressWarnings("unchecked")
    @Override
    default <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        AnnotatedElement ae = toAnnotatedElement();
        if (ae == null)
            return (T[]) new Object[0];
        else
            return ae.getAnnotationsByType(annotationClass);
    }

    @Override
    default <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        AnnotatedElement ae = toAnnotatedElement();
        if (ae == null)
            return null;
        else
            return ae.getDeclaredAnnotation(annotationClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    default <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        AnnotatedElement ae = toAnnotatedElement();
        if (ae == null)
            return (T[]) new Object[0];
        else
            return ae.getDeclaredAnnotationsByType(annotationClass);
    }

    @Override
    default Annotation[] getDeclaredAnnotations() {
        AnnotatedElement ae = toAnnotatedElement();
        if (ae == null)
            return new Annotation[0];
        else
            return ae.getDeclaredAnnotations();
    }

}
