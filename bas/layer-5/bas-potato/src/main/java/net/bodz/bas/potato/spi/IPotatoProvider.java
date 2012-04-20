package net.bodz.bas.potato.spi;

import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.potato.traits.IConstructorMap;
import net.bodz.bas.potato.traits.IEventMap;
import net.bodz.bas.potato.traits.IMethodMap;
import net.bodz.bas.potato.traits.IPropertyMap;
import net.bodz.bas.potato.traits.IType;

public interface IPotatoProvider {

    IType getType(Class<?> objType)
            throws QueryException;

    IPropertyMap getPropertyMap(Class<?> objType)
            throws QueryException;

    IMethodMap getMethodMap(Class<?> objType)
            throws QueryException;

    IConstructorMap getConstructorMap(Class<?> objType)
            throws QueryException;

    IEventMap getEventMap(Class<?> objType)
            throws QueryException;

}
