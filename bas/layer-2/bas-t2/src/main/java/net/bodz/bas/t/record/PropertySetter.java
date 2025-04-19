package net.bodz.bas.t.record;

import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IPropertyDescriptor;

public class PropertySetter<T, E>
        implements IColumnSetter<T, E> {

    final IPropertyDescriptor property;
    final Method writeMethod;

    public PropertySetter(IPropertyDescriptor property) {
        this.property = property;
        this.writeMethod = property.getWriteMethod();
    }

    @Override
    public void set(T context, E value) {
        try {
            writeMethod.invoke(context, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "set " + writeMethod.getDeclaringClass().getSimpleName() + "::" + property.getName();
    }

}
