package net.bodz.bas.c.type;

import java.util.HashMap;
import java.util.Map;

public class TypeNearby {

    // String prefix;
    private String suffix;
    private boolean decodeIInterface;
    private boolean cacheEnabled;

    private Map<Class<?>, Object> cache;
    private static Object NONE = new Object();

    public TypeNearby(String suffix, boolean cacheEnabled) {
        if (suffix == null)
            throw new NullPointerException("suffix");
        this.suffix = suffix;
        this.cacheEnabled = cacheEnabled;

        if (cacheEnabled)
            cache = new HashMap<Class<?>, Object>();
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isDecodeIInterface() {
        return decodeIInterface;
    }

    public void setDecodeIInterface(boolean decodeIInterface) {
        this.decodeIInterface = decodeIInterface;
    }

    public Class<?> find(Class<?> class0) {
        if (class0 == null)
            throw new NullPointerException("clazz");

        if (cacheEnabled) {
            Object nearby = cache.get(class0);
            if (nearby != null)
                return nearby != NONE ? (Class<?>) nearby : null;
        }

        String simpleName = class0.getSimpleName();
        String fqcn0 = class0.getName();

        if (class0.isInterface() && decodeIInterface) {
            if (simpleName.charAt(0) == 'I' //
                    && simpleName.length() > 1 //
                    && Character.isUpperCase(simpleName.charAt(1)))
                fqcn0 = class0.getPackage().getName() + "." + simpleName.substring(1);
        }

        String nearbyFQCN = fqcn0 + suffix;
        Class<?> nearby;
        try {
            nearby = Class.forName(nearbyFQCN);
        } catch (ClassNotFoundException e) {
            nearby = null;
        }

        if (cacheEnabled)
            cache.put(class0, nearby != null ? nearby : NONE);

        return nearby;
    }

}
