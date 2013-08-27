package net.bodz.bas.potato.element;

import java.util.Map;

/**
 * General purpose feature provider.
 */
public interface IFeatureProvider {

    Map<Class<?>, Object> getFeatureMap();

    <T> T getFeature(Class<T> featureClass);

    <T> void setFeature(Class<T> featureClass, T feature);

}
