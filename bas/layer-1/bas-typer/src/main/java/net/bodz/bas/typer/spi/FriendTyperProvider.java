package net.bodz.bas.typer.spi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;

/**
 * @see net.bodz.bas.c.type.TypeNearby
 */
public class FriendTyperProvider
        extends AbstractTyperProvider {

    static final Logger logger = Logger.getLogger(FriendTyperProvider.class.getName());

    private final int priority;

    private final String prefixName;
    private final String suffixFamilyName = "Typers";
    private final boolean flatten;

    private Map<Class<?>, Object> friendTyperInstantiationCache = new HashMap<Class<?>, Object>();

    public FriendTyperProvider() {
        this.priority = BuiltinProviderOrder.friend.getPriority();
        this.prefixName = "";
        this.flatten = false;
    }

    public FriendTyperProvider(String searchPackageName, int priority) {
        this(searchPackageName, priority, false);
    }

    public FriendTyperProvider(String searchPackageName, int priority, boolean flatten) {
        if (searchPackageName == null)
            throw new NullPointerException("searchPackageName");
        this.priority = priority;
        this.prefixName = searchPackageName + ".";
        this.flatten = flatten;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException {
        return getTyper(objType, typerClass);
    }

    @Override
    public <T> T getTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException {
        Class<?> queryType = objType;
        while (queryType != null) {
            String typeName = flatten ? queryType.getSimpleName() : queryType.getName();

            String simpleTyperName = typerClass.getSimpleName();
            if (isStandardInterfaceName(simpleTyperName))
                simpleTyperName = simpleTyperName.substring(1);

            String friendTyperName = prefixName + typeName + simpleTyperName;
            T typer = loadTyper(friendTyperName, typerClass, objType);
            if (typer != null)
                return typer;

            String friendTyperFamilyName = prefixName + typeName + suffixFamilyName;
            typer = loadTyper(friendTyperFamilyName, typerClass, objType);
            if (typer != null)
                return typer;

            queryType = queryType.getSuperclass();
        }
        return null;
    }

    public <T> T loadTyper(String friendTyperImplName, Class<T> typerClass, Class<?> objClass)
            throws QueryException {
        Class<?> friendTyperImplClass;
        try {
            friendTyperImplClass = Class.forName(friendTyperImplName);
        } catch (ClassNotFoundException e) {
            return null;
        }

        if (friendTyperImplClass.isInterface())
            return null;
        int modifiers = friendTyperImplClass.getModifiers();
        if (Modifier.isAbstract(modifiers))
            return null;

        Object friendTyperImpl = friendTyperInstantiationCache.get(friendTyperImplClass);
        if (friendTyperImpl == null)
            try {
                for (Constructor<?> ctor : friendTyperImplClass.getConstructors()) {
                    Class<?>[] pv = ctor.getParameterTypes();
                    if (pv.length == 0) { // default ctor()
                        friendTyperImpl = ctor.newInstance();
                        break;
                    }
                    if (pv.length == 1 && pv[0] == Class.class) { // ctor(val_t.class)
                        friendTyperImpl = ctor.newInstance(objClass);
                        break;
                    }
                }
                if (friendTyperImpl == null) {
                    logger.fine("No useful constructor: " + friendTyperImplClass);
                    return null;
                }
                friendTyperInstantiationCache.put(friendTyperImplClass, friendTyperImpl);
            } catch (ReflectiveOperationException e) {
                throw new QueryException(e);
            }

        if (typerClass.isInstance(friendTyperImpl))
            return typerClass.cast(friendTyperImpl);

        if (friendTyperImpl instanceof IQueryable) {
            IQueryable queryable = (IQueryable) friendTyperImpl;
            return queryable.query(typerClass);
        }

        return null;
    }

    private static boolean isStandardInterfaceName(String name) {
        if (name.startsWith("I"))
            if (name.length() > 1)
                if (Character.isUpperCase(name.charAt(1)))
                    return true;
        return false;
    }

}
