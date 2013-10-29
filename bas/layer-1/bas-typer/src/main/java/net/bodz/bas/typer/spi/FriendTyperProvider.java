package net.bodz.bas.typer.spi;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;

public class FriendTyperProvider
        extends AbstractTyperProvider {

    private final int priority;

    private final String prefixName;
    private final String suffixFamilyName = "Typers";
    private final boolean flatten;

    private Map<Class<?>, Object> friendTyperMap = new HashMap<Class<?>, Object>();

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
        String objTypeName = flatten ? objType.getSimpleName() : objType.getName();

        String simpleTyperName = typerClass.getSimpleName();
        if (isStandardInterfaceName(simpleTyperName))
            simpleTyperName = simpleTyperName.substring(1);

        String friendTyperName = prefixName + objTypeName + simpleTyperName;
        T typer = loadTyper(friendTyperName, typerClass);
        if (typer != null)
            return typer;

        String friendTyperFamilyName = prefixName + objTypeName + suffixFamilyName;
        typer = loadTyper(friendTyperFamilyName, typerClass);
        if (typer != null)
            return typer;

        return null;
    }

    public <T> T loadTyper(String friendTyperImplName, Class<T> typerClass)
            throws QueryException {
        Class<?> friendTyperImplClass;
        try {
            friendTyperImplClass = Class.forName(friendTyperImplName);
        } catch (ClassNotFoundException e) {
            return null;
        }

        Object friendTyperImpl = friendTyperMap.get(friendTyperImplClass);
        if (friendTyperImpl == null)
            try {
                friendTyperImpl = friendTyperImplClass.newInstance();
                friendTyperMap.put(friendTyperImplClass, friendTyperImpl);
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
