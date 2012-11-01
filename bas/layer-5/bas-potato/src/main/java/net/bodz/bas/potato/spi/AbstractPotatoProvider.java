package net.bodz.bas.potato.spi;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.potato.model.IConstructorMap;
import net.bodz.bas.potato.model.IEventMap;
import net.bodz.bas.potato.model.IMethodMap;
import net.bodz.bas.potato.model.IPropertyMap;
import net.bodz.bas.potato.model.IType;
import net.bodz.bas.trait.spi.AbstractTraitsProvider;

public abstract class AbstractPotatoProvider
        extends AbstractTraitsProvider
        implements IPotatoProvider {

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
                _traits = getPropertyMap(objType);
                break;

            case methodMapTraits:
                _traits = getMethodMap(objType);
                break;

            case constructorMapTraits:
                _traits = getConstructorMap(objType);
                break;

            case eventMapTraits:
                _traits = getEventMap(objType);
                break;

            default:
                return null;
            }
            return traitsType.cast(_traits);
        }
        return null;
    }

}
