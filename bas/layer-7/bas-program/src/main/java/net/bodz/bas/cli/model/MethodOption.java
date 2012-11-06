package net.bodz.bas.cli.model;

import java.lang.reflect.Method;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.potato.model.IProperty;

public class MethodOption
        extends AbstractOption {

    final Method method;

    public MethodOption(Method method) {
        super(method.getDeclaringClass(), method.getName(), method, method.getReturnType());
        this.method = method;
    }

    @Override
    public IProperty property() {
        throw new NotImplementedException();
    }

}
