package net.bodz.bas.i18n.nls;

import java.util.Locale;

public final class CombinedNLS
        extends AbstractNLS {

    private final NLS[] dicts;

    public CombinedNLS(Locale preferredLocale, NLS parent, NLS... dicts) {
        super(parent, preferredLocale);
        if (dicts == null)
            throw new NullPointerException("dicts");
        this.dicts = dicts;
    }

    @Override
    public void setPreferredLocale(Locale preferredLocale) {
        super.setPreferredLocale(preferredLocale);
        for (int i = 0; i < dicts.length; i++) {
            NLS dict = dicts[i];
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
        for (int i = 0; i < dicts.length; i++) {
            if (i != 0)
                buf.append('+');
            buf.append(dicts[i].getName());
        }
        return buf.toString();
    }

    @Override
    protected boolean localContainsKey(String key) {
        for (int i = 0; i < dicts.length; i++)
            if (dicts[i].containsKey(key))
                return true;
        return false;
    }

    @Override
    protected Object localGet(String key) {
        for (int i = 0; i < dicts.length; i++) {
            NLS dict = dicts[i];
            Object value = dict.get(key);
            if (value != null)
                return value;
        }
        return null;
    }

    @Override
    protected Object localGet(String key, Object def) {
        Object value = localGet(key);
        return value == null ? def : value;
    }

}
