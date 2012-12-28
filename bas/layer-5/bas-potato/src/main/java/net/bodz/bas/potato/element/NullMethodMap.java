package net.bodz.bas.potato.element;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.c.reflect.MethodSignature;

public class NullMethodMap
        implements IMethodMap {

    @Override
    public int getMethodCount() {
        return 0;
    }

    @Override
    public Collection<IMethod> getMethods() {
        return Collections.emptyList();
    }

    @Override
    public boolean containsMethod(MethodSignature signature) {
        return false;
    }

    @Override
    public IMethod getMethod(MethodSignature signature) {
        return null;
    }

    static final NullMethodMap instance = new NullMethodMap();

    public static NullMethodMap getInstance() {
        return instance;
    }

}
