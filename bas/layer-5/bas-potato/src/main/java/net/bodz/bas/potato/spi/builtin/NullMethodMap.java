package net.bodz.bas.potato.spi.builtin;

import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.model.IMethod;
import net.bodz.bas.potato.model.IMethodMap;

public class NullMethodMap
        implements IMethodMap {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<IMethod> getMethods() {
        return Collections.emptyList();
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
