package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.lang.QueryException;
import net.bodz.bas.traits.AbstractTraitsProvider;
import net.bodz.bas.traits.util.TraitsImplUtil;

public class FriendTraitsProvider
        extends AbstractTraitsProvider {

    private final int priority;

    private final String prefixName;
    private final String commonSuffixName = "Traits";

    public FriendTraitsProvider() {
        this.prefixName = "";
        this.priority = BuiltinProviderOrder.friend.getPriority();
    }

    public FriendTraitsProvider(String searchPackageName, int priority) {
        if (searchPackageName == null)
            throw new NullPointerException("searchPackageName");
        this.prefixName = searchPackageName + ".";
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        String objTypeName = objType.getName();

        String simpleTraitsTypeName = traitsType.getSimpleName();
        if (isStandardInterfaceName(simpleTraitsTypeName))
            simpleTraitsTypeName = simpleTraitsTypeName.substring(1);

        String perfectFriendName = prefixName + objTypeName + simpleTraitsTypeName;
        T traits = checkoutTraits(perfectFriendName, traitsType);
        if (traits != null)
            return traits;

        String commonFriendName = prefixName + objTypeName + commonSuffixName;
        traits = checkoutTraits(commonFriendName, traitsType);
        if (traits != null)
            return traits;

        return null;
    }

    public <T> T checkoutTraits(String friendTraitsTypeName, Class<T> traitsType)
            throws QueryException {
        Class<?> friendTraitsType;
        try {
            friendTraitsType = Jdk7Reflect.forName(friendTraitsTypeName);
        } catch (ClassNotFoundException e) {
            return null;
        }
        try {
            return TraitsImplUtil.getTraits(friendTraitsType, traitsType);
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
