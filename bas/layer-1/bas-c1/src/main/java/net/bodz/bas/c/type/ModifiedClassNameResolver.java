package net.bodz.bas.c.type;

import java.util.HashMap;
import java.util.Map;

public class ModifiedClassNameResolver
        implements
            IClassFunction {

    private ClassLoader classLoader;

    // Class<T> paramClass;
    // Class<R> returnClass;

    private final int parents;
    private final String prefix;
    private final String suffix;
    private boolean decodeIInterface;
    private boolean cacheEnabled;

    private Map<Class<?>, Class<?>> cache;

    public ModifiedClassNameResolver(String prefix, String suffix, boolean cacheEnabled) {
        this(null, 0, prefix, suffix, cacheEnabled);
    }

    public ModifiedClassNameResolver(ClassLoader classLoader, int parents, String prefix, String suffix,
            boolean cacheEnabled) {
        if (suffix == null)
            throw new NullPointerException("suffix");
        if (suffix.isEmpty())
            throw new IllegalArgumentException("suffix is empty.");
        this.classLoader = classLoader;
        this.parents = parents;
        this.prefix = prefix;
        this.suffix = suffix;
        this.cacheEnabled = cacheEnabled;

        if (cacheEnabled)
            cache = new HashMap<>();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public int getParents() {
        return parents;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public boolean isDecodeIInterface() {
        return decodeIInterface;
    }

    public void setDecodeIInterface(boolean decodeIInterface) {
        this.decodeIInterface = decodeIInterface;
    }

    @Override
    public Class<?> apply(Class<?> src) {
        if (src == null)
            throw new NullPointerException("src");

        Class<?> target = null;
        if (cacheEnabled) {
            target = cache.get(src);
            if (target != null || cache.containsKey(src))
                return target;
        }

        String base = src.getSimpleName();

        if (src.isInterface() && decodeIInterface) {
            if (base.charAt(0) == 'I' //
                    && base.length() > 1 //
                    && Character.isUpperCase(base.charAt(1)))
                base = base.substring(1);
        }

        String dstName;
        {
            Package pkg = src.getPackage();
            String pkgName = pkg == null ? null : pkg.getName();
            if (pkgName != null) {
                if (pkgName.isEmpty())
                    pkgName = null;
                else
                    pkgName = getParentName(pkgName, parents);
            }

            StringBuilder sb = new StringBuilder();
            if (pkgName != null) {
                sb.append(pkgName);
                sb.append('.');
            }
            if (prefix != null)
                sb.append(prefix);
            sb.append(base);
            sb.append(suffix);
            dstName = sb.toString();
        }

        Class<?> dst;
        try {
            if (classLoader == null)
                dst = Class.forName(dstName, true, src.getClassLoader());
            else
                dst = Class.forName(dstName, true, classLoader);
        } catch (ClassNotFoundException e) {
            dst = null;
        }

        if (cacheEnabled)
            cache.put(src, dst);

        return dst;
    }

    static String getParentName(String pkgName, int n) {
        for (int i = 0; i < n; i++) {
            int lastDot = pkgName.lastIndexOf('.');
            if (lastDot != -1)
                pkgName = pkgName.substring(0, lastDot);
            else
                return null;
        }
        return pkgName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            sb.append(prefix);
            sb.append(" :: ");
        }
        sb.append(suffix);
        return sb.toString();
    }

}
