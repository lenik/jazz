package net.bodz.lily.entity.type;

import java.lang.reflect.Method;

public class SourceDeclaredPropertyOrder
        extends AbstractSourceDeclaredMethodOrder<ColumnProperty> {

    public SourceDeclaredPropertyOrder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected Method getMethod(ColumnProperty obj) {
        return obj.getReadMethod();
    }

}