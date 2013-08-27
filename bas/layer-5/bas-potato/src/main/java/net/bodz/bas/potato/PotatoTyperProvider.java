package net.bodz.bas.potato;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.potato.element.IConstructorMap;
import net.bodz.bas.potato.element.IEventMap;
import net.bodz.bas.potato.element.IMethodMap;
import net.bodz.bas.potato.element.IPropertyMap;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.spi.AbstractTyperProvider;

public class PotatoTyperProvider
        extends AbstractTyperProvider {

    static final int typeTypers = 0;
    static final int propertyMapTypers = 1;
    static final int methodMapTypers = 2;
    static final int constructorMapTypers = 3;
    static final int eventMapTypers = 4;

    static final Map<String, Integer> rindexMap;
    static {
        rindexMap = new HashMap<String, Integer>();
        rindexMap.put(IType.class.getName(), typeTypers);
        rindexMap.put(IPropertyMap.class.getName(), propertyMapTypers);
        rindexMap.put(IMethodMap.class.getName(), methodMapTypers);
        rindexMap.put(IConstructorMap.class.getName(), constructorMapTypers);
        rindexMap.put(IEventMap.class.getName(), eventMapTypers);
    }

    @Override
    public <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException {
        return getTyper(objType, typerClass);
    }

    @Override
    public <T> T getTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException {
        Integer index = rindexMap.get(typerClass.getName());
        if (index != null) {
            Object _typers;
            switch (index) {
            case typeTypers:
                _typers = getType(objType);
                break;

            case propertyMapTypers:
                _typers = getType(objType).getPropertyMap();
                break;

            case methodMapTypers:
                _typers = getType(objType).getMethodMap();
                break;

            case constructorMapTypers:
                _typers = getType(objType).getConstructorMap();
                break;

            case eventMapTypers:
                _typers = getType(objType).getEventMap();
                break;

            default:
                return null;
            }
            return typerClass.cast(_typers);
        }
        return null;
    }

    public IType getType(Class<?> objType) {
        return PotatoLoader.getType(objType);
    }

}
