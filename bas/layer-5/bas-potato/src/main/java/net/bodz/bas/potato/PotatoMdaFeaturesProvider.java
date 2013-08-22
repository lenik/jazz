package net.bodz.bas.potato;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.mf.spi.AbstractMdaFeaturesProvider;
import net.bodz.bas.potato.element.IConstructorMap;
import net.bodz.bas.potato.element.IEventMap;
import net.bodz.bas.potato.element.IMethodMap;
import net.bodz.bas.potato.element.IPropertyMap;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.rtx.QueryException;

public class PotatoMdaFeaturesProvider
        extends AbstractMdaFeaturesProvider {

    static final int typeMdaFeatures = 0;
    static final int propertyMapMdaFeatures = 1;
    static final int methodMapMdaFeatures = 2;
    static final int constructorMapMdaFeatures = 3;
    static final int eventMapMdaFeatures = 4;

    static final Map<String, Integer> mdaFeaturesFastIndexMap;
    static {
        mdaFeaturesFastIndexMap = new HashMap<String, Integer>();
        mdaFeaturesFastIndexMap.put(IType.class.getName(), typeMdaFeatures);
        mdaFeaturesFastIndexMap.put(IPropertyMap.class.getName(), propertyMapMdaFeatures);
        mdaFeaturesFastIndexMap.put(IMethodMap.class.getName(), methodMapMdaFeatures);
        mdaFeaturesFastIndexMap.put(IConstructorMap.class.getName(), constructorMapMdaFeatures);
        mdaFeaturesFastIndexMap.put(IEventMap.class.getName(), eventMapMdaFeatures);
    }

    @Override
    public <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeaturesType)
            throws QueryException {
        Integer mdaFeaturesIndex = mdaFeaturesFastIndexMap.get(mdaFeaturesType.getName());
        if (mdaFeaturesIndex != null) {
            Object _mdaFeatures;
            switch (mdaFeaturesIndex) {
            case typeMdaFeatures:
                _mdaFeatures = getType(objType);
                break;

            case propertyMapMdaFeatures:
                _mdaFeatures = getType(objType).getPropertyMap();
                break;

            case methodMapMdaFeatures:
                _mdaFeatures = getType(objType).getMethodMap();
                break;

            case constructorMapMdaFeatures:
                _mdaFeatures = getType(objType).getConstructorMap();
                break;

            case eventMapMdaFeatures:
                _mdaFeatures = getType(objType).getEventMap();
                break;

            default:
                return null;
            }
            return mdaFeaturesType.cast(_mdaFeatures);
        }
        return null;
    }

    public IType getType(Class<?> objType) {
        return PotatoLoader.getType(objType);
    }

}
