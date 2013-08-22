package net.bodz.bas.mf.spi;

import net.bodz.bas.meta.lang.MdaFeatureClass;
import net.bodz.bas.rtx.IQueryable;

public class AnnotationMdaFeaturesProvider
        extends AbstractMdaFeaturesProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.annotation.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeatureType) {
        if (objType == null)
            throw new NullPointerException("objType");
        MdaFeatureClass mdaFeatureClassAnnotation = objType.getAnnotation(MdaFeatureClass.class);
        if (mdaFeatureClassAnnotation == null)
            return null;

        Class<? extends IQueryable> mdaFeatureClass = mdaFeatureClassAnnotation.value();
        assert mdaFeatureClass != null;

        try {
            IQueryable queryable = mdaFeatureClass.newInstance();
            return queryable.query(mdaFeatureType);
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
