package net.bodz.lily.entity.type;

import java.lang.reflect.Method;

public class SourceDeclaredMethodOrder
        extends AbstractSourceDeclaredMethodOrder<Method> {

    public SourceDeclaredMethodOrder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected Method getMethod(Method obj) {
        return obj;
    }

}