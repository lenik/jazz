package net.bodz.bas.trait.spi;

import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.trait.util.TraitImplCache;

public class FriendTraitsProvider
        extends AbstractTraitsProvider {

    private final int priority;

    private final String prefixName;
    private final String commonSuffixName = "Traits";
    private final boolean flatten;

    public FriendTraitsProvider() {
        this.priority = BuiltinProviderOrder.friend.getPriority();
        this.prefixName = "";
        this.flatten = false;
    }

    public FriendTraitsProvider(String searchPackageName, int priority) {
        this(searchPackageName, priority, false);
    }

    public FriendTraitsProvider(String searchPackageName, int priority, boolean flatten) {
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
    public <T> T getTrait(Class<?> objType, Class<T> traitType)
            throws QueryException {
        String objTypeName = flatten ? objType.getSimpleName() : objType.getName();

        String simpleTraitsTypeName = traitType.getSimpleName();
        if (isStandardInterfaceName(simpleTraitsTypeName))
            simpleTraitsTypeName = simpleTraitsTypeName.substring(1);

        String perfectFriendName = prefixName + objTypeName + simpleTraitsTypeName;
        T traits = checkoutTraits(perfectFriendName, traitType);
        if (traits != null)
            return traits;

        String commonFriendName = prefixName + objTypeName + commonSuffixName;
        traits = checkoutTraits(commonFriendName, traitType);
        if (traits != null)
            return traits;

        return null;
    }

    public <T> T checkoutTraits(String friendTraitTypeName, Class<T> traitType)
            throws QueryException {
        Class<?> friendTraitType;
        try {
            friendTraitType = Class.forName(friendTraitTypeName);
        } catch (ClassNotFoundException e) {
            return null;
        }
        try {
            return TraitImplCache.getTrait(friendTraitType, traitType);
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
