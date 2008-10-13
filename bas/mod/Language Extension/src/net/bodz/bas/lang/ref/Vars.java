package net.bodz.bas.lang.ref;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.lang.a.ReadOnly;
import net.bodz.bas.lang.err.ReadOnlyException;
import net.bodz.bas.lang.util.Reflects;

public class Vars {

    public static class FieldMeta implements VarMeta {

        protected final Field field;
        protected boolean     readOnly;

        public FieldMeta(Field field, boolean readOnly) {
            assert field != null;
            this.field = field;
            this.readOnly = readOnly;
        }

        public FieldMeta(Field field) {
            this(field, field.isAnnotationPresent(ReadOnly.class));
        }

        @Override
        public String getName() {
            return field.getName();
        }

        @Override
        public Class<?> getType() {
            return field.getType();
        }

        @Override
        public int getModifiers() {
            return field.getModifiers();
        }

        @Override
        public boolean isReadOnly() {
            return readOnly;
        }

        // AnnotatedElement

        @Override
        public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
            return field.getAnnotation(annotationClass);
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
        public boolean isAnnotationPresent(
                Class<? extends Annotation> annotationClass) {
            return field.isAnnotationPresent(annotationClass);
        }

    }

    public static class FieldVar<T> extends _Var<T> {

        protected FieldMeta meta;
        protected Object    ctx;

        public FieldVar(FieldMeta meta, Object object) {
            this.meta = meta;
            this.ctx = object;
        }

        public FieldVar(Field field, Object object) {
            this(new FieldMeta(field), object);
        }

        @Override
        public VarMeta getMeta() {
            return meta;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T get() {
            return (T) Reflects.get(ctx, meta.field);
        }

        @Override
        public void set(Object value) {
            Reflects.set(ctx, meta.field, value);
        }

    }

    /**
     * Only annotations on read method are used.
     */
    public static class PropertyMeta implements VarMeta {

        protected final PropertyDescriptor property;
        protected final Method             readf;
        protected final Method             writef;

        public PropertyMeta(PropertyDescriptor property) {
            this.property = property;
            readf = property.getReadMethod();
            writef = property.getWriteMethod();
            if (readf == null)
                throw new UnsupportedOperationException(
                        "unreadable property isn't supported: "
                                + property.getName());
        }

        @Override
        public String getName() {
            return property.getName();
        }

        @Override
        public Class<?> getType() {
            return property.getPropertyType();
        }

        @Override
        public int getModifiers() {
            return readf.getModifiers();
        }

        @Override
        public boolean isReadOnly() {
            return writef == null;
        }

        // AnnotatedElement
        @Override
        public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
            return readf.getAnnotation(annotationClass);
        }

        @Override
        public Annotation[] getAnnotations() {
            return readf.getAnnotations();
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return readf.getDeclaredAnnotations();
        }

        @Override
        public boolean isAnnotationPresent(
                Class<? extends Annotation> annotationClass) {
            return readf.isAnnotationPresent(annotationClass);
        }

    }

    public static class PropertyVar<T> extends _Var<T> {

        protected final PropertyMeta meta;
        protected Object             ctx;

        public PropertyVar(PropertyMeta meta, Object object) {
            assert meta != null;
            this.meta = meta;
            this.ctx = object;
        }

        public PropertyVar(PropertyDescriptor property, Object object) {
            this(new PropertyMeta(property), object);
        }

        @Override
        public VarMeta getMeta() {
            return meta;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T get() {
            return (T) Reflects.invoke(ctx, meta.readf);
        }

        @Override
        public void set(T value) {
            if (meta.writef == null)
                throw new ReadOnlyException("unwritable property: "
                        + meta.getName());
            Reflects.invoke(ctx, meta.writef, value);
        }

    }

    public static <T> Var<T> wrap(Object object, Field field) {
        return new FieldVar<T>(field, object);
    }

    public static <T> Var<T> wrap(Object object, PropertyDescriptor property) {
        return new PropertyVar<T>(property, object);
    }

}
