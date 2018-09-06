package net.bodz.bas.potato;

import net.bodz.bas.potato.element.IType;

public abstract class AbstractTypeProvider
        implements ITypeProvider {

    private final int infoset;

    public AbstractTypeProvider(int infoset) {
        this.infoset = infoset;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public IType loadType(Class<?> clazz) {
        return loadType(clazz, null, infoset);
    }

    @Override
    public IType loadType(Class<?> clazz, Object obj) {
        return loadType(clazz, obj, infoset);
    }

}
