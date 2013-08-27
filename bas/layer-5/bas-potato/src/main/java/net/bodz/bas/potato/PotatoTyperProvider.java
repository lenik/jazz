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

    private static final int typeTyper = 0;
    private static final int propertyMapTyper = 1;
    private static final int methodMapTyper = 2;
    private static final int constructorMapTyper = 3;
    private static final int eventMapTyper = 4;

    static final Map<String, Integer> rindexMap;
    static {
        rindexMap = new HashMap<String, Integer>();
        rindexMap.put(IType.class.getName(), typeTyper);
        rindexMap.put(IPropertyMap.class.getName(), propertyMapTyper);
        rindexMap.put(IMethodMap.class.getName(), methodMapTyper);
        rindexMap.put(IConstructorMap.class.getName(), constructorMapTyper);
        rindexMap.put(IEventMap.class.getName(), eventMapTyper);
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
            case typeTyper:
                _typers = getType(objType);
                break;

            case propertyMapTyper:
                _typers = getType(objType).getPropertyMap();
                break;

            case methodMapTyper:
                _typers = getType(objType).getMethodMap();
                break;

            case constructorMapTyper:
                _typers = getType(objType).getConstructorMap();
                break;

            case eventMapTyper:
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
