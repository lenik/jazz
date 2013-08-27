package net.bodz.bas.typer.spi;

import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.util.TyperImplCache;

public class FriendTyperProvider
        extends AbstractTyperProvider {

    private final int priority;

    private final String prefixName;
    private final String commonSuffixName = "Typers";
    private final boolean flatten;

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

        String simpleTypersTypeName = typerClass.getSimpleName();
        if (isStandardInterfaceName(simpleTypersTypeName))
            simpleTypersTypeName = simpleTypersTypeName.substring(1);

        String perfectFriendName = prefixName + objTypeName + simpleTypersTypeName;
        T typers = checkoutTypers(perfectFriendName, typerClass);
        if (typers != null)
            return typers;

        String commonFriendName = prefixName + objTypeName + commonSuffixName;
        typers = checkoutTypers(commonFriendName, typerClass);
        if (typers != null)
            return typers;

        return null;
    }

    public <T> T checkoutTypers(String friendTyperTypeName, Class<T> typerClass)
            throws QueryException {
        Class<?> friendTyperType;
        try {
            friendTyperType = Class.forName(friendTyperTypeName);
        } catch (ClassNotFoundException e) {
            return null;
        }
        try {
            return TyperImplCache.getTyper(friendTyperType, typerClass);
        } catch (ReflectiveOperationException e) {
            throw new QueryException(e.getMessage(), e);
        }
    }

    private static boolean isStandardInterfaceName(String name) {
        if (name.startsWith("I"))
            if (name.length() > 1)
                if (Character.isUpperCase(name.charAt(1)))
                    return true;
        return false;
    }

}
