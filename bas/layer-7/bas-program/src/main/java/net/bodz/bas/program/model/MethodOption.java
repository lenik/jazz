package net.bodz.bas.program.model;

import java.lang.reflect.Method;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.mda.xjdoc.model.MethodDoc;

public class MethodOption
        extends AbstractOption {

    private final Method method;

    public MethodOption(Method method, MethodDoc methodDoc) {
        super(method.getName(), methodDoc, method.getGenericReturnType());
        this.method = method;
    }

    @Override
    public IProperty property() {
        throw new NotImplementedException();
    }

}
