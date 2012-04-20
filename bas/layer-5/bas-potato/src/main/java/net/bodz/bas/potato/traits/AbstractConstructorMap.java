package net.bodz.bas.potato.traits;

import java.util.TreeMap;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.MethodSignatureComparator;

public class AbstractConstructorMap
        extends TreeMap<MethodSignature, IConstructor>
        implements IConstructorMap {

    private static final long serialVersionUID = 1L;

    public AbstractConstructorMap() {
        super(MethodSignatureComparator.getInstance());
    }

    @Override
    public IConstructor getConstructor(MethodSignature signature) {
        return get((Object) signature);
    }

}
