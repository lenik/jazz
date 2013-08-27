package net.bodz.bas.potato;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.tf.spi.AbstractTypeFeatureProvider;
import net.bodz.bas.potato.element.IConstructorMap;
import net.bodz.bas.potato.element.IEventMap;
import net.bodz.bas.potato.element.IMethodMap;
import net.bodz.bas.potato.element.IPropertyMap;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.rtx.QueryException;

public class PotatoTypeFeatureProvider
        extends AbstractTypeFeatureProvider {

    static final int typeTypeFeatures = 0;
    static final int propertyMapTypeFeatures = 1;
    static final int methodMapTypeFeatures = 2;
    static final int constructorMapTypeFeatures = 3;
    static final int eventMapTypeFeatures = 4;

    static final Map<String, Integer> rindexMap;
    static {
        rindexMap = new HashMap<String, Integer>();
        rindexMap.put(IType.class.getName(), typeTypeFeatures);
        rindexMap.put(IPropertyMap.class.getName(), propertyMapTypeFeatures);
        rindexMap.put(IMethodMap.class.getName(), methodMapTypeFeatures);
        rindexMap.put(IConstructorMap.class.getName(), constructorMapTypeFeatures);
        rindexMap.put(IEventMap.class.getName(), eventMapTypeFeatures);
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Object obj, Class<T> featureType)
            throws QueryException {
        return getTypeFeature(objType, featureType);
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Class<T> featureType)
            throws QueryException {
        Integer index = rindexMap.get(featureType.getName());
        if (index != null) {
            Object _typeFeatures;
            switch (index) {
            case typeTypeFeatures:
                _typeFeatures = getType(objType);
                break;

            case propertyMapTypeFeatures:
                _typeFeatures = getType(objType).getPropertyMap();
                break;

            case methodMapTypeFeatures:
                _typeFeatures = getType(objType).getMethodMap();
                break;

            case constructorMapTypeFeatures:
                _typeFeatures = getType(objType).getConstructorMap();
                break;

            case eventMapTypeFeatures:
                _typeFeatures = getType(objType).getEventMap();
                break;

            default:
                return null;
            }
            return featureType.cast(_typeFeatures);
        }
        return null;
    }

    public IType getType(Class<?> objType) {
        return PotatoLoader.getType(objType);
    }

}
