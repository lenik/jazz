package net.bodz.bas.lang.ref;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.lang.err.ReadOnlyException;
import net.bodz.bas.lang.util.Reflects;

public class Accessors {

    static class FieldAccessor<T> extends _Accessor<T> {

        private final Field field;
        private Object      object;

        public FieldAccessor(Field field, Object object) {
            this.field = field;
            this.object = object;
        }

        @Override
        public Class<?> getType() {
            return field.getDeclaringClass();
        }

        @SuppressWarnings("unchecked")
        @Override
        public T get() {
            return (T) Reflects.get(object, field);
        }

        @Override
        public void set(T value) {
            Reflects.set(object, field, value);
        }

    }

    static class PropertyAccessor<T> extends _Accessor<T> {

        private final PropertyDescriptor property;
        private Object                   object;

        public PropertyAccessor(PropertyDescriptor property, Object object) {
            this.property = property;
            this.object = object;
        }

        @Override
        public Class<?> getType() {
            return property.getPropertyType();
        }

        @Override
        public boolean isReadable() {
            return property.getReadMethod() != null;
        }

        @Override
        public boolean isWritable() {
            return property.getWriteMethod() != null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T get() {
            Method readf = property.getReadMethod();
            if (readf == null)
                throw new UnsupportedOperationException("unreadable property: "
                        + property);
            return (T) Reflects.invoke(object, readf);
        }

        @Override
        public void set(T value) {
            Method writef = property.getWriteMethod();
            if (writef == null)
                throw new ReadOnlyException("unwritable property: " + property);
            Reflects.invoke(object, writef, value);
        }

    }

    public static <T> Accessor<T> access(Object object, Field field) {
        return new FieldAccessor<T>(field, object);
    }

    public static <T> Accessor<T> access(Object object,
            PropertyDescriptor property) {
        return new PropertyAccessor<T>(property, object);
    }

}
