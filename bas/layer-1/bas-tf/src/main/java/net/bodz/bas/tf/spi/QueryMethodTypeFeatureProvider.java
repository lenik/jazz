package net.bodz.bas.tf.spi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.rtx.QueryException;

/**
 * This provider allow user to define type features by declare a
 * {@link #getTypeFeature(Class, Object, Class)} method as:
 * 
 * <pre>
 * public static &lt;T&gt; T query(Class&lt;T&gt; typeFeatureClass) {
 *     // ...
 * }
 * </pre>
 * 
 * Here, both <code>public</code> and <code>static</code> is optional.
 */
public class QueryMethodTypeFeatureProvider
        extends AbstractTypeFeatureProvider {

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
    public <T> T getTypeFeature(Class<?> objType, Class<T> typeFeatureClass)
            throws QueryException {
        Method staticMethod = findQueryMethod(objType);
        if (staticMethod == null)
            return null;
        boolean isStatic = Modifier.isStatic(staticMethod.getModifiers());
        if (!isStatic)
            return null;

        try {
            Object returnValue = staticMethod.invoke(null, typeFeatureClass);
            T typeFeatures = typeFeatureClass.cast(returnValue);
            return typeFeatures;
        } catch (ReflectiveOperationException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Object obj, Class<T> typeFeatureClass)
            throws QueryException {
        Method method = findQueryMethod(objType);
        if (method == null)
            return null;

        try {
            Object returnValue = method.invoke(obj, typeFeatureClass);
            T typeFeature = typeFeatureClass.cast(returnValue);
            return typeFeature;
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
