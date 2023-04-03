package net.bodz.uni.shelj.codegen.java.ioforms;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class VarField
        implements
            AnnotatedElement {

    public final Class<?> type;
    public final int modifiers;
    public final AnnotatedElement annotations;
    public final String name;

    public VarField(Field field, String name) {
        this.type = field.getType();
        this.modifiers = field.getModifiers();
        this.annotations = field;
        this.name = name;

    }

    public VarField(Class<?> type, int modifiers, String name) {
        this.type = type;
        this.modifiers = modifiers;
        this.annotations = null;
        this.name = name;
    }

    public boolean isFinal() {
        return Modifier.isFinal(modifiers);
    }

    public boolean isNotFinal() {
        return !Modifier.isFinal(modifiers);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        if (annotations == null)
            return false;
        else
            return annotations.isAnnotationPresent(annotationClass);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        if (annotations == null)
            return null;
        else
            return annotations.getAnnotation(annotationClass);
    }

    static final Annotation[] EMPTY = new Annotation[0];

    @Override
    public Annotation[] getAnnotations() {
        if (annotations == null)
            return EMPTY;
        else
            return annotations.getAnnotations();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        if (annotations == null)
            return (T[]) EMPTY;
        else
            return annotations.getAnnotationsByType(annotationClass);
    }

    @Override
    public <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        if (annotations == null)
            return null;
        else
            return annotations.getDeclaredAnnotation(annotationClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        if (annotations == null)
            return (T[]) EMPTY;
        else
            return annotations.getDeclaredAnnotationsByType(annotationClass);
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        if (annotations == null)
            return EMPTY;
        else
            return annotations.getDeclaredAnnotations();
    }

    @Override
    public String toString() {
        return name + ": " + type.getName();
    }

}
