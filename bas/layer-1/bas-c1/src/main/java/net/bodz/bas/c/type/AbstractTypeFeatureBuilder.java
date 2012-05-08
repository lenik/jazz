package net.bodz.bas.c.type;

public abstract class AbstractTypeFeatureBuilder
        implements ITypeFeatureBuilder {

    final Class<?> featureClass;

    public AbstractTypeFeatureBuilder(Class<?> featureClass) {
        if (featureClass == null)
            throw new NullPointerException("featureClass");
        this.featureClass = featureClass;
    }

    @Override
    public Class<?> getFeatureClass() {
        return featureClass;
    }

}
