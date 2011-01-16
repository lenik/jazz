package net.bodz.bas.potato.traits;

import java.util.TreeMap;

import net.bodz.bas.potato.traits.util.MethodKeyComparator;

public abstract class AbstractMethodMap
        extends TreeMap<MethodKey, IMethod>
        implements IMethodMap {

    private static final long serialVersionUID = 1L;

    public AbstractMethodMap() {
        super(MethodKeyComparator.getInstance());
    }

    @Override
    public IMethod getMethod(MethodKey methodKey) {
        return get((Object) methodKey);
    }

}
