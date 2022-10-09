package net.bodz.lily.entity.type;

import java.lang.reflect.Method;

public class ReflectMethodOrder
        extends AbstractReflectMethodOrder<Method> {

    public ReflectMethodOrder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected Method getMethod(Method obj) {
        return obj;
    }

}
