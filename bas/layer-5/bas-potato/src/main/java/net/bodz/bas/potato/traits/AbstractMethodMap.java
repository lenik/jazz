package net.bodz.bas.potato.traits;

import java.util.TreeMap;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.MethodSignatureComparator;

public abstract class AbstractMethodMap
        extends TreeMap<MethodSignature, IMethod>
        implements IMethodMap {

    private static final long serialVersionUID = 1L;

    public AbstractMethodMap() {
        super(MethodSignatureComparator.getInstance());
    }

    @Override
    public IMethod getMethod(MethodSignature signature) {
        return get((Object) signature);
    }

}
