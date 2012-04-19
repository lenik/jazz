package net.bodz.bas.traits.provider.builtin;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.traits.AbstractTraitsProvider;

/**
 * The GetTraitsMethod traits provider allow user to define type traits by declare a getTraits
 * method as:
 * 
 * <pre>
 * public static &lt;T&gt; T query(Class&lt;T&gt; traitsType) {
 *     // ...
 * }
 * </pre>
 * 
 * Here, both <code>public</code> and <code>static</code> is optional.
 */
public class QueryMethodTraitsProvider
        extends AbstractTraitsProvider {

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
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        Method staticMethod = findQueryMethod(objType);
        if (staticMethod == null)
            return null;
        boolean isStatic = Modifier.isStatic(staticMethod.getModifiers());
        if (!isStatic)
            return null;

        try {
            Object returnValue = staticMethod.invoke(null, traitsType);
            T traits = traitsType.cast(returnValue);
            return traits;
        } catch (ReflectiveOperationException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        Method method = findQueryMethod(objType);
        if (method == null)
            return null;

        try {
            Object returnValue = method.invoke(obj, traitsType);
            T traits = traitsType.cast(returnValue);
            return traits;
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
