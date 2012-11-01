package net.bodz.bas.potato.model;

import java.util.Map;

/**
 * 
 */
public interface IFeatureProvider {

    Map<Class<?>, Object> getFeatureMap();

    <T> T getFeature(Class<T> featureClass);

    <T> void setFeature(Class<T> featureClass, T feature);

}
