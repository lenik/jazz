package net.bodz.bas.text.locale;

import net.bodz.bas.a.A_bas;

public abstract class _NLSDict implements NLSDict {

    private final String title;

    public _NLSDict(String title) {
        if (title == null)
            title = A_bas.getDisplayName(getClass());
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public Object get(String key) {
        return get(key, null);
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
    public String format(String key, Object... args) {
        String format = getString(key);
        if (format == null)
            throw new NullPointerException("key isn't existed: " + key); //$NON-NLS-1$
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
        return "NLSDict: " + getTitle();
    }

}
