package net.bodz.bas.traits.provider.builtin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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

    public static String GET_TRAITS_METHOD = "getTraits";

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.PRIORITY_GETMETHOD;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        Method staticMethod = findGetTraitsMethod(objType, true);
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
        Method method = findGetTraitsMethod(objType, false);
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

    private static Method findGetTraitsMethod(Class<?> objType, boolean wantStatic) {
        Method method = null;
        Class<?> clazz = null;

        while (true) {
            if (clazz == null)
                clazz = objType;
            else {
                clazz = clazz.getSuperclass();
                if (clazz == null || clazz == Object.class)
                    return null;
            }

            try {
                method = clazz.getDeclaredMethod(GET_TRAITS_METHOD, Class.class);
            } catch (NoSuchMethodException e) {
                continue;
            }

            // Check the method signature.
            if (wantStatic) {
                int modifiers = method.getModifiers();
                boolean isStatic = Modifier.isStatic(modifiers);
                if (!isStatic)
                    continue;
            }
            break;
        }
        method.setAccessible(true);
        return method;
    }

}
