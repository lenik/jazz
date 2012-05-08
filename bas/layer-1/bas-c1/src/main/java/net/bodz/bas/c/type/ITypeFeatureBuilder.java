package net.bodz.bas.c.type;

public interface ITypeFeatureBuilder {

    Class<?> getFeatureClass();

    Object buildTypeFeature(Class<?> type);

}
