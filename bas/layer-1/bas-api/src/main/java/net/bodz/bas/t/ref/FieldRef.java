package net.bodz.bas.t.ref;

import java.lang.reflect.Field;

public class FieldRef<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    private final Object object;
    private final Field field;

    /**
     * @param object
     *            may be <code>null</code> if <code>field</code> refers to a static field.
     */
    public FieldRef(Object object, Field field, Class<T> fieldType) {
        this.object = object;
        this.field = field;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends T> getValueType() {
        return (Class<? extends T>) field.getType();
    }

    @Override
    public T get() {
        Object fieldValue;
        try {
            fieldValue = field.get(object);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return getValueType().cast(fieldValue);
    }

    @Override
    public void set(T value) {
        try {
            field.set(object, value);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(object);
    }

}
