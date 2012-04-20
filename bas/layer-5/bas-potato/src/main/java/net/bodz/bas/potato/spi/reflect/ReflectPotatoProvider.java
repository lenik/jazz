package net.bodz.bas.potato.spi.reflect;

import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.potato.spi.AbstractPotatoProvider;
import net.bodz.bas.potato.traits.IConstructorMap;
import net.bodz.bas.potato.traits.IEventMap;
import net.bodz.bas.potato.traits.IMethodMap;
import net.bodz.bas.potato.traits.IPropertyMap;
import net.bodz.bas.potato.traits.IType;

public class ReflectPotatoProvider
        extends AbstractPotatoProvider {

    @Override
    public IType getType(Class<?> objType)
            throws QueryException {
        return new ReflectType(objType);
    }

    @Override
    public IPropertyMap getPropertyMap(Class<?> objType)
            throws QueryException {
        return new ReflectPropertyMap(objType);
    }

    @Override
    public IMethodMap getMethodMap(Class<?> objType)
            throws QueryException {
        return new ReflectMethodMap(objType);
    }

    @Override
    public IConstructorMap getConstructorMap(Class<?> objType)
            throws QueryException {
        return new ReflectConstructorMap(objType);
    }

    @Override
    public IEventMap getEventMap(Class<?> objType)
            throws QueryException {
        return null;
    }

}
