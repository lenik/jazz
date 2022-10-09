package net.bodz.lily.entity.type;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.t.order.IOrdinal;

public class ColumnProperty
        implements
            IOrdinal {

    PropertyDescriptor propertyDescriptor;
    Method readMethod;
    Method writeMethod;

    Column column;
    int ordinal;
    int priority;

    public ColumnProperty(PropertyDescriptor propertyDescriptor) {
        if (propertyDescriptor == null)
            throw new NullPointerException("propertyDescriptor");

        this.propertyDescriptor = propertyDescriptor;
        readMethod = propertyDescriptor.getReadMethod();
        writeMethod = propertyDescriptor.getWriteMethod();

        Method method = readMethod != null ? readMethod : writeMethod;
        if (method != null) {
            column = method.getAnnotation(Column.class);

            Ordinal aOrdinal = readMethod.getAnnotation(Ordinal.class);
            if (aOrdinal != null)
                ordinal = aOrdinal.value();

            Priority aPriority = readMethod.getAnnotation(Priority.class);
            if (aPriority != null)
                priority = aPriority.value();
        }
    }

    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }

    public String getName() {
        return propertyDescriptor.getName();
    }

    public Class<?> getPropertyType() {
        return propertyDescriptor.getPropertyType();
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    public int getPriority() {
        return priority;
    }

}
