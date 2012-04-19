package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import net.bodz.bas.potato.traits.AbstractProperty;

public class ReflectProperty
        extends AbstractProperty {

    private final Field field;

    /**
     * @throws NullPointerException
     *             If <code>field</code> is <code>null</code>.
     */
    public ReflectProperty(Field field) {
        super(field.getDeclaringClass(), field.getName());
        this.field = field;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public Object get(Object instance)
            throws ReflectiveOperationException {
        return field.get(instance);
    }

    @Override
    public void set(Object instance, Object value)
            throws ReflectiveOperationException {
        field.set(instance, value);
    }

    @Override
    public Annotation[] getAnnotations() {
        return field.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return field.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return field.isAnnotationPresent(annotationClass);
    }

}
