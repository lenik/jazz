package net.bodz.bas.tf.spi;

import net.bodz.bas.meta.lang.TypeFeatureClass;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;

public class AnnotationTypeFeatureProvider
        extends AbstractTypeFeatureProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.annotation.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Class<T> typeFeatureClass) {
        if (objType == null)
            throw new NullPointerException("objType");
        TypeFeatureClass _typeFeatureClass = objType.getAnnotation(TypeFeatureClass.class);
        if (_typeFeatureClass == null)
            return null;

        Class<? extends IQueryable> annotatedClass = _typeFeatureClass.value();
        assert annotatedClass != null;

        try {
            IQueryable queryable = annotatedClass.newInstance();
            return queryable.query(typeFeatureClass);
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Object obj, Class<T> typeFeatureClass)
            throws QueryException {
        return getTypeFeature(objType, typeFeatureClass);
    }

}
