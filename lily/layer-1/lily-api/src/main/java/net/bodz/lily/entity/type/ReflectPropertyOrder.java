package net.bodz.lily.entity.type;

import java.lang.reflect.Method;

public class ReflectPropertyOrder
        extends AbstractReflectMethodOrder<ColumnProperty> {

    public ReflectPropertyOrder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected Method getMethod(ColumnProperty obj) {
        return obj.getReadMethod();
    }

}