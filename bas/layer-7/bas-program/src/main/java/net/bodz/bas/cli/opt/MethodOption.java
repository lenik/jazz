package net.bodz.bas.cli.opt;

import java.lang.reflect.Method;

public class MethodOption
        extends AbstractOption {

    Method method;

    public MethodOption(Method method) {
        super(method.getDeclaringClass(), method.getName(), method, method.getReturnType());
    }

}
