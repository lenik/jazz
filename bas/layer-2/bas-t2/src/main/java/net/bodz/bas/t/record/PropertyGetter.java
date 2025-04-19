package net.bodz.bas.t.record;

import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IPropertyDescriptor;

public class PropertyGetter<T, E>
        implements IColumnGetter<T, E> {

    final IPropertyDescriptor property;
    final Method readMethod;

    public PropertyGetter(IPropertyDescriptor property) {
        this.property = property;
        this.readMethod = property.getReadMethod();
    }

    @Override
    public E get(Object context) {
        try {
            @SuppressWarnings("unchecked")
            E val = (E) readMethod.invoke(context);
            return val;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "get " + readMethod.getDeclaringClass().getSimpleName() + "::" + property.getName();
    }

}
