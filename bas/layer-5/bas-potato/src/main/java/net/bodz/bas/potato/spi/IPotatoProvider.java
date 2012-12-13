package net.bodz.bas.potato.spi;

import net.bodz.bas.potato.model.IConstructorMap;
import net.bodz.bas.potato.model.IEventMap;
import net.bodz.bas.potato.model.IMethodMap;
import net.bodz.bas.potato.model.IPropertyMap;
import net.bodz.bas.potato.model.IType;
import net.bodz.bas.rtx.QueryException;

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
