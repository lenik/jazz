package net.bodz.bas.potato.traits;

import java.util.TreeMap;

import net.bodz.bas.potato.traits.util.ConstructorKeyComparator;

public class AbstractConstructorMap
        extends TreeMap<ConstructorKey, IConstructor>
        implements IConstructorMap {

    private static final long serialVersionUID = 1L;

    public AbstractConstructorMap() {
        super(ConstructorKeyComparator.getInstance());
    }

    @Override
    public IConstructor getConstructor(ConstructorKey constructorKey) {
        return get((Object) constructorKey);
    }

}
