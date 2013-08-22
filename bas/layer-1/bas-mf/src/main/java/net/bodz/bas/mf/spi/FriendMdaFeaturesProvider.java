package net.bodz.bas.mf.spi;

import net.bodz.bas.mf.util.MdaFeatureImplCache;
import net.bodz.bas.rtx.QueryException;

public class FriendMdaFeaturesProvider
        extends AbstractMdaFeaturesProvider {

    private final int priority;

    private final String prefixName;
    private final String commonSuffixName = "MdaFeatures";
    private final boolean flatten;

    public FriendMdaFeaturesProvider() {
        this.priority = BuiltinProviderOrder.friend.getPriority();
        this.prefixName = "";
        this.flatten = false;
    }

    public FriendMdaFeaturesProvider(String searchPackageName, int priority) {
        this(searchPackageName, priority, false);
    }

    public FriendMdaFeaturesProvider(String searchPackageName, int priority, boolean flatten) {
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
    public <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeatureType)
            throws QueryException {
        String objTypeName = flatten ? objType.getSimpleName() : objType.getName();

        String simpleMdaFeaturesTypeName = mdaFeatureType.getSimpleName();
        if (isStandardInterfaceName(simpleMdaFeaturesTypeName))
            simpleMdaFeaturesTypeName = simpleMdaFeaturesTypeName.substring(1);

        String perfectFriendName = prefixName + objTypeName + simpleMdaFeaturesTypeName;
        T mdaFeatures = checkoutMdaFeatures(perfectFriendName, mdaFeatureType);
        if (mdaFeatures != null)
            return mdaFeatures;

        String commonFriendName = prefixName + objTypeName + commonSuffixName;
        mdaFeatures = checkoutMdaFeatures(commonFriendName, mdaFeatureType);
        if (mdaFeatures != null)
            return mdaFeatures;

        return null;
    }

    public <T> T checkoutMdaFeatures(String friendMdaFeatureTypeName, Class<T> mdaFeatureType)
            throws QueryException {
        Class<?> friendMdaFeatureType;
        try {
            friendMdaFeatureType = Class.forName(friendMdaFeatureTypeName);
        } catch (ClassNotFoundException e) {
            return null;
        }
        try {
            return MdaFeatureImplCache.getMdaFeature(friendMdaFeatureType, mdaFeatureType);
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
