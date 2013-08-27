package net.bodz.bas.tf.spi;

import net.bodz.bas.tf.util.TypeFeatureImplCache;
import net.bodz.bas.rtx.QueryException;

public class FriendTypeFeatureProvider
        extends AbstractTypeFeatureProvider {

    private final int priority;

    private final String prefixName;
    private final String commonSuffixName = "TypeFeatures";
    private final boolean flatten;

    public FriendTypeFeatureProvider() {
        this.priority = BuiltinProviderOrder.friend.getPriority();
        this.prefixName = "";
        this.flatten = false;
    }

    public FriendTypeFeatureProvider(String searchPackageName, int priority) {
        this(searchPackageName, priority, false);
    }

    public FriendTypeFeatureProvider(String searchPackageName, int priority, boolean flatten) {
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
    public <T> T getTypeFeature(Class<?> objType, Object obj, Class<T> typeFeatureClass)
            throws QueryException {
        return getTypeFeature(objType, typeFeatureClass);
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Class<T> typeFeatureClass)
            throws QueryException {
        String objTypeName = flatten ? objType.getSimpleName() : objType.getName();

        String simpleTypeFeaturesTypeName = typeFeatureClass.getSimpleName();
        if (isStandardInterfaceName(simpleTypeFeaturesTypeName))
            simpleTypeFeaturesTypeName = simpleTypeFeaturesTypeName.substring(1);

        String perfectFriendName = prefixName + objTypeName + simpleTypeFeaturesTypeName;
        T typeFeatures = checkoutTypeFeatures(perfectFriendName, typeFeatureClass);
        if (typeFeatures != null)
            return typeFeatures;

        String commonFriendName = prefixName + objTypeName + commonSuffixName;
        typeFeatures = checkoutTypeFeatures(commonFriendName, typeFeatureClass);
        if (typeFeatures != null)
            return typeFeatures;

        return null;
    }

    public <T> T checkoutTypeFeatures(String friendTypeFeatureTypeName, Class<T> typeFeatureClass)
            throws QueryException {
        Class<?> friendTypeFeatureType;
        try {
            friendTypeFeatureType = Class.forName(friendTypeFeatureTypeName);
        } catch (ClassNotFoundException e) {
            return null;
        }
        try {
            return TypeFeatureImplCache.getTypeFeature(friendTypeFeatureType, typeFeatureClass);
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
