package net.bodz.bas.api;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.bodz.bas.api.types.NLS;
import net.bodz.bas.api.types.ResourceBundleNLS;

/**
 * To make the default {@link #getPackageName()} and all the related methods work, your direct
 * derivation of this abstract class should be declared as <code>final</code>.
 */
public abstract class AbstractModuleInfo
        implements IModuleInfo {

    private NLS defaultNLS;
    private Map<Locale, NLS> localeNLS;

    public AbstractModuleInfo() {
    }

    public String getPackageName() {
        return getClass().getPackage().getName();
    }

    public NLS getNLS() {
        if (defaultNLS == null)
            defaultNLS = createNLS(Locale.getDefault());
        return defaultNLS;
    }

    public NLS getNLS(Locale locale) {
        if (locale == null)
            throw new NullPointerException("locale");
        if (locale.equals(Locale.getDefault()))
            return getNLS();

        if (localeNLS == null)
            localeNLS = new HashMap<Locale, NLS>();

        NLS nls = localeNLS.get(locale);
        if (nls == null) {
            nls = createNLS(locale);
            localeNLS.put(locale, nls);
        }
        return nls;
    }

    protected NLS createNLS(Locale locale) {
        String defaultBaseName = getPackageName() + ".NLS";
        NLS nls = new ResourceBundleNLS(defaultBaseName, locale);
        return nls;
    }

}
