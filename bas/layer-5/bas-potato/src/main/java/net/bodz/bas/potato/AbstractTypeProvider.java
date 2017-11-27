package net.bodz.bas.potato;

import net.bodz.bas.potato.element.IType;

public abstract class AbstractTypeProvider
        implements ITypeProvider {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public IType loadType(Class<?> clazz) {
        return loadType(clazz, null, -1);
    }

    @Override
    public IType loadType(Class<?> clazz, Object obj) {
        return loadType(clazz, obj, -1);
    }

}
