package net.bodz.bas.commons.ref;

import java.lang.reflect.Field;

import net.bodz.bas.commons.helpinfo.ObjectInfo;

public class FieldRef<T>
        implements Ref<T> {

    private final Object object;
    private final Field field;
    private final Class<T> fieldType;

    /**
     * @param object
     *            may be <code>null</code> if <code>field</code> refers to a static field.
     */
    public FieldRef(Object object, Field field, Class<T> fieldType) {
        if (field == null)
            throw new NullPointerException("field");
        this.object = object;
        this.field = field;
        this.fieldType = fieldType;
    }

    @Override
    public T get() {
        Object fieldValue;
        try {
            fieldValue = field.get(object);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return fieldType.cast(fieldValue);
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
        Object id = ObjectInfo.getObjectId(object);
        return "Field(" + field + ") of " + id;
    }

}
