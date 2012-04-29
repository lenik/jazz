package net.bodz.bas.disp.util;

import java.lang.reflect.Method;

public class ClassMethod {

    private final Class<?> type;
    private final Method method;

    public ClassMethod(Class<?> type, Method method) {
        assert type != null;
        assert method != null;

        this.type = type;
        this.method = method;
    }

    public Class<?> getType() {
        return type;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return method.toString();
    }

}
