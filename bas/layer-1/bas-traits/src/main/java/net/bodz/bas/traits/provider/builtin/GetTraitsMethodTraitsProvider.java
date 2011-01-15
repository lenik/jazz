package net.bodz.bas.traits.provider.builtin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bodz.bas.lang.QueryException;
import net.bodz.bas.traits.provider.AbstractTraitsProvider;

/**
 * The GetTraitsMethod traits provider allow user to define type traits by declare a getTraits
 * method as:
 * 
 * <pre>
 * public static &lt;T&gt; T getTraits(Class&lt;T&gt; traitsType) {
 *     // ...
 * }
 * </pre>
 * 
 * Here, both <code>public</code> and <code>static</code> is optional.
 */
public class GetTraitsMethodTraitsProvider
        extends AbstractTraitsProvider {

    public static String getTraitsMethodName = "getTraits";

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.getTraitsMethod.getPriority();
    }

    @Override
    public boolean isDefined() {
        return true;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        Method staticMethod = findGetTraitsMethod(objType);
        if (staticMethod == null)
            return null;

        try {
            Object returnValue = staticMethod.invoke(null, traitsType);
            T traits = traitsType.cast(returnValue);
            return traits;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new QueryException(e);
        }
    }

    @Override
    public <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        Method method = findGetTraitsMethod(objType);
        if (method == null)
            return null;

        try {
            Object returnValue = method.invoke(obj, traitsType);
            T traits = traitsType.cast(returnValue);
            return traits;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new QueryException(e);
        }
    }

    private static Method findGetTraitsMethod(Class<?> objType) {
        Method method;
        try {
            method = objType.getMethod(getTraitsMethodName, Class.class);
        } catch (NoSuchMethodException e) {
            try {
                method = objType.getDeclaredMethod(getTraitsMethodName, Class.class);
                method.setAccessible(true);
            } catch (NoSuchMethodException e1) {
                return null;
            }
        }
        return method;
    }

}
