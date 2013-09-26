package net.bodz.bas.typer.spi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.rtx.QueryException;

/**
 * This provider allow user to define typers by declare a {@link #getTyper(Class, Object, Class)}
 * method as:
 * 
 * <pre>
 * public static &lt;T&gt; T query(Class&lt;T&gt; typerClass) {
 *     // ...
 * }
 * </pre>
 * 
 * Here, both <code>public</code> and <code>static</code> is optional.
 */
public class QueryMethodTyperProvider
        extends AbstractTyperProvider {

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
    public <T> T getTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException {
        Method staticMethod = findQueryMethod(objType);
        if (staticMethod == null)
            return null;
        boolean isStatic = Modifier.isStatic(staticMethod.getModifiers());
        if (!isStatic)
            return null;

        try {
            Object returnValue = staticMethod.invoke(null, typerClass);
            T typers = typerClass.cast(returnValue);
            return typers;
        } catch (ReflectiveOperationException e) {
            throw new QueryException(e);
        }
    }

    @Override
    public <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException {
        Method method = findQueryMethod(objType);
        if (method == null)
            return null;

        try {
            Object returnValue = method.invoke(obj, typerClass);
            T typer = typerClass.cast(returnValue);
            return typer;
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
