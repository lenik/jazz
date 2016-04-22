package net.bodz.bas.c.type;

import java.util.HashMap;
import java.util.Map;

public class NameConventionTypeMapper
        implements ITypeMapper {

    private ClassLoader classLoader;
    private String prefix;
    private String suffix;
    private boolean decodeIInterface;
    private boolean cacheEnabled;

    private Map<Class<?>, Class<?>> cache;
    private static Class<?> NONE = void.class;

    public NameConventionTypeMapper(ClassLoader classLoader, String prefix, String suffix, boolean cacheEnabled) {
        if (suffix == null)
            throw new NullPointerException("suffix");
        if (suffix.isEmpty())
            throw new IllegalArgumentException("suffix is empty.");
        this.classLoader = classLoader;
        this.prefix = prefix;
        this.suffix = suffix;
        this.cacheEnabled = cacheEnabled;

        if (cacheEnabled)
            cache = new HashMap<Class<?>, Class<?>>();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    @Override
    public Class<?> map(Class<?> src) {
        if (src == null)
            throw new NullPointerException("src");

        Class<?> target = NONE;
        if (cacheEnabled) {
            target = cache.get(src);
            if (target != null)
                return target != NONE ? target : null;
        }

        String base = src.getSimpleName();

        if (src.isInterface() && decodeIInterface) {
            if (base.charAt(0) == 'I' //
                    && base.length() > 1 //
                    && Character.isUpperCase(base.charAt(1)))
                base = base.substring(1);
        }

        String dstName;
        if (prefix == null) {
            String fqcn = src.getName();
            dstName = fqcn + suffix;
        } else {
            Package pkg = src.getPackage();
            StringBuilder sb = new StringBuilder();
            if (pkg != null) {
                sb.append(pkg.getName());
                sb.append('.');
            }
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
            cache.put(src, dst != null ? dst : NONE);

        return dst;
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
