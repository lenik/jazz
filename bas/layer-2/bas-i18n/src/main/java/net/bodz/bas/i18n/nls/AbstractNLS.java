package net.bodz.bas.i18n.nls;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.i18n.LocaleColo;
import net.bodz.bas.util.variant.AbstractVariantLookupMap;

public abstract class AbstractNLS
        extends AbstractVariantLookupMap<String>
        implements NLS {

    private final NLS parent;

    private Locale preferredLocale;
    private String name;

    public AbstractNLS() {
        this(null, LocaleColo.getInstance().get());
    }

    /**
     * This constructor doesn't call {@link #reload(Locale)}.
     */
    public AbstractNLS(NLS parent) {
        this(parent, LocaleColo.getInstance().get());
    }

    /**
     * This constructor doesn't call {@link #reload(Locale)}.
     */
    public AbstractNLS(NLS parent, Locale preferredLocale) {
        this.parent = parent;
        this.preferredLocale = preferredLocale;

        String simpleName = getClass().getSimpleName();
        if (simpleName.endsWith("NLS"))
            simpleName = simpleName.substring(0, simpleName.length() - 3);
        name = Strings.hyphenatize(simpleName);
    }

    @Override
    public NLS getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    @Override
    public String getPath() {
        StringBuilder buf = new StringBuilder(100);
        NLS node = this;
        do {
            String nodeName = node.getName();

            if (buf.length() != 0)
                buf.append('/');
            buf.append(Strings.reverse(nodeName));

            node = node.getParent();
        } while (node != null);

        buf.reverse();
        return buf.toString();
    }

    @Override
    public Locale getPreferredLocale() {
        return preferredLocale;
    }

    @Override
    public void setPreferredLocale(Locale preferredLocale) {
        this.preferredLocale = preferredLocale;
        try {
            reload();
        } catch (Exception e) {
            // XXX - exception buffer...
            // throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Reload the dict.
     * 
     * @throws Exception
     *             If failed to reload for the specified locale.
     */
    protected abstract void reload()
            throws Exception;

    protected abstract String localName();

    /**
     * The local contains() implementation for the dict chain.
     * 
     * @def localGet(key) != null
     */
    protected boolean localContainsKey(String key) {
        return localGet(key) != null;
    }

    /**
     * The local get(key, def) implementation for the dict chain.
     */
    protected abstract Object localGet(String key, Object def);

    /**
     * The local get(key) implementation for the dict chain.
     * 
     * @def localGet(key, null)
     */
    protected Object localGet(String key) {
        return localGet(key, null);
    }

    @Deprecated
    @Override
    public final Set<String> keySet() {
        return Collections.emptySet();
    }

    @Override
    public final boolean containsKey(String key) {
        if (localContainsKey(key))
            return true;
        if (parent != null)
            return parent.containsKey(key);
        return false;
    }

    @Override
    public final Object get(String key) {
        Object value = localGet(key);
        if (value != null || localContainsKey(key))
            return value;
        if (parent != null)
            return parent.get(key);
        return null;
    }

    @Override
    public final Object get(String key, Object def) {
        Object value = localGet(key);
        if (value != null || localContainsKey(key))
            return value;
        if (parent != null)
            return parent.get(key, def);
        return def;
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public String getString(String key, String def) {
        Object value = get(key);
        if (value == null)
            return def;
        String s = String.valueOf(value);
        return s;
    }

    @Override
    public final String format(String key, Object... args) {
        String format = getString(key);
        if (format == null)
            throw new NullPointerException("key isn't existed: " + key);
        String s = String.format(format, args);
        return s;
    }

    @Override
    public String tr(String name) {
        if (name == null)
            return null;
        String s = getString(name);
        return s != null ? s : name;
    }

    @Override
    public String toString() {
        return getPath();
    }

}