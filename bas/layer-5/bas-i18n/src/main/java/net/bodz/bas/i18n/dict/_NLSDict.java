package net.bodz.bas.i18n.dict;

import net.bodz.bas.meta.info.DisplayNameUtil;

public abstract class _NLSDict
        implements NLSDict {

    private final String title;
    private final NLSDict next;

    public _NLSDict(String title) {
        this(title, null);
    }

    public _NLSDict(String title, NLSDict next) {
        if (title == null)
            title = DisplayNameUtil.getDisplayName(getClass());
        this.title = title;
        this.next = next;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public final boolean contains(String key) {
        if (_contains(key))
            return true;
        if (next != null)
            return next.contains(key);
        return false;
    }

    protected boolean _contains(String key) {
        return get(key) != null;
    }

    @Override
    public final Object get(String key) {
        Object value = _get(key);
        if (value != null || _contains(key))
            return value;
        if (next != null)
            return next.get(key);
        return null;
    }

    @Override
    public final Object get(String key, Object def) {
        Object value = _get(key);
        if (value != null || _contains(key))
            return value;
        if (next != null)
            return next.get(key, def);
        return def;
    }

    protected Object _get(String key) {
        return _get(key, null);
    }

    protected abstract Object _get(String key, Object def);

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
        String s = "NLSDict: " + getTitle();
        if (next != null)
            s += "\n" + next;
        return s;
    }

}
