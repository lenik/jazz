package net.bodz.bas.mf.spi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.rtx.QueryException;

/**
 * The GetMdaFeaturesMethod mdaFeatures provider allow user to define type mdaFeatures by declare a getMdaFeatures
 * method as:
 * 
 * <pre>
 * public static &lt;T&gt; T query(Class&lt;T&gt; mdaFeatureType) {
 *     // ...
 * }
 * </pre>
 * 
 * Here, both <code>public</code> and <code>static</code> is optional.
 */
public class QueryMethodMdaFeaturesProvider
        extends AbstractMdaFeaturesProvider {

    public static String queryMethodName = "query";

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.queryMethod.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeatureType)
            throws QueryException {
        Method staticMethod = findQueryMethod(objType);
        if (staticMethod == null)
            return null;
        boolean isStatic = Modifier.isStatic(staticMethod.getModifiers());
        if (!isStatic)
            return null;

        try {
            Object returnValue = staticMethod.invoke(null, mdaFeatureType);
            T mdaFeatures = mdaFeatureType.cast(returnValue);
            return mdaFeatures;
        } catch (ReflectiveOperationException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public <T> T getMdaFeature(Class<?> objType, Object obj, Class<T> mdaFeatureType)
            throws QueryException {
        Method method = findQueryMethod(objType);
        if (method == null)
            return null;

        try {
            Object returnValue = method.invoke(obj, mdaFeatureType);
            T mdaFeature = mdaFeatureType.cast(returnValue);
            return mdaFeature;
        } catch (ReflectiveOperationException e) {
            throw new QueryException(e);
        }
    }

    private static Method findQueryMethod(Class<?> objType) {
        Method method;
        try {
            method = objType.getMethod(queryMethodName, Class.class);
        } catch (NoSuchMethodException e) {
            try {
                method = objType.getDeclaredMethod(queryMethodName, Class.class);
                method.setAccessible(true);
            } catch (NoSuchMethodException e1) {
                return null;
            }
        }
        return method;
    }

}
