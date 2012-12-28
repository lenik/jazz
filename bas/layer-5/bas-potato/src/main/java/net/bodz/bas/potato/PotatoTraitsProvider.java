package net.bodz.bas.potato;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.potato.element.IConstructorMap;
import net.bodz.bas.potato.element.IEventMap;
import net.bodz.bas.potato.element.IMethodMap;
import net.bodz.bas.potato.element.IPropertyMap;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.trait.spi.AbstractTraitsProvider;

public class PotatoTraitsProvider
        extends AbstractTraitsProvider {

    static final int typeTraits = 0;
    static final int propertyMapTraits = 1;
    static final int methodMapTraits = 2;
    static final int constructorMapTraits = 3;
    static final int eventMapTraits = 4;

    static final Map<String, Integer> traitsFastIndexMap;
    static {
        traitsFastIndexMap = new HashMap<String, Integer>();
        traitsFastIndexMap.put(IType.class.getName(), typeTraits);
        traitsFastIndexMap.put(IPropertyMap.class.getName(), propertyMapTraits);
        traitsFastIndexMap.put(IMethodMap.class.getName(), methodMapTraits);
        traitsFastIndexMap.put(IConstructorMap.class.getName(), constructorMapTraits);
        traitsFastIndexMap.put(IEventMap.class.getName(), eventMapTraits);
    }

    @Override
    public <T> T getTrait(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        Integer traitsIndex = traitsFastIndexMap.get(traitsType.getName());
        if (traitsIndex != null) {
            Object _traits;
            switch (traitsIndex) {
            case typeTraits:
                _traits = getType(objType);
                break;

            case propertyMapTraits:
                _traits = getType(objType).getPropertyMap();
                break;

            case methodMapTraits:
                _traits = getType(objType).getMethodMap();
                break;

            case constructorMapTraits:
                _traits = getType(objType).getConstructorMap();
                break;

            case eventMapTraits:
                _traits = getType(objType).getEventMap();
                break;

            default:
                return null;
            }
            return traitsType.cast(_traits);
        }
        return null;
    }

    public IType getType(Class<?> objType) {
        return PotatoLoader.getType(objType);
    }

}
