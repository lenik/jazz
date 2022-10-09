package net.bodz.lily.entity.type;

import java.lang.reflect.Method;

public abstract class AbstractReflectMethodOrder<T>
        extends AbstractMethodOrder<T> {

    public AbstractReflectMethodOrder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected void findMethods(Class<?> clazz) {
        for (Method m : clazz.getDeclaredMethods()) {
            methodNames.put(m.getName(), ++nextOrder);
        }
    }

}