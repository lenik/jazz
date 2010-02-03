package net.bodz.bas.potato.adapter.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import net.bodz.bas.potato.AbstarctPotatoProperty;
import net.bodz.bas.potato.PotatoException;

public class ReflectPotatoProperty
        extends AbstarctPotatoProperty {

    private ReflectPotatoType<?> declaringPotatoType;
    private final Field field;

    /**
     * @throws NullPointerException
     *             If <code>field</code> is <code>null</code>.
     */
    public ReflectPotatoProperty(Field field) {
        super(field.getName());
        this.field = field;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public ReflectPotatoType<?> getDeclaringType() {
        return declaringPotatoType;
    }

    @Override
    public Object get(Object instance)
            throws PotatoException {
        try {
            return field.get(instance);
        } catch (Exception e) {
            throw new PotatoException(e.getMessage(), e);
        }
    }

    @Override
    public void set(Object instance, Object value)
            throws PotatoException {
        try {
            field.set(instance, value);
        } catch (Exception e) {
            throw new PotatoException(e.getMessage(), e);
        }
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
