package net.bodz.bas.i18n.nls;

import java.util.Locale;

import net.bodz.bas.i18n.LocaleCtl;

public final class CombinedNLS
        extends AbstractNLS {

    private final NLS[] children;

    public CombinedNLS(NLS parent, NLS... children) {
        this(LocaleCtl.LOCALE.get(), parent, children);
    }

    public CombinedNLS(Locale preferredLocale, NLS parent, NLS... children) {
        super(parent, preferredLocale);
        if (children == null)
            throw new NullPointerException("children");
        this.children = children;
    }

    @Override
    public void setPreferredLocale(Locale preferredLocale) {
        super.setPreferredLocale(preferredLocale);
        for (int i = 0; i < children.length; i++) {
            NLS dict = children[i];
            dict.setPreferredLocale(preferredLocale);
        }
    }

    @Override
    protected void reload()
            throws Exception {
    }

    @Override
    protected String localName() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < children.length; i++) {
            if (i != 0)
                buf.append('+');
            buf.append(children[i].getName());
        }
        return buf.toString();
    }

    @Override
    protected boolean localContainsKey(Object key) {
        for (int i = 0; i < children.length; i++)
            if (children[i].containsKey(key))
                return true;
        return false;
    }

    @Override
    protected Object localGet(Object key) {
        for (int i = 0; i < children.length; i++) {
            NLS dict = children[i];
            Object value = dict.get(key);
            if (value != null)
                return value;
        }
        return null;
    }

    @Override
    protected Object localGet(Object key, Object def) {
        Object value = localGet(key);
        return value == null ? def : value;
    }

}
