package net.bodz.bas.api;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.bodz.bas.i18n.NLS;

public abstract class AbstractModuleInfo
        implements IModuleInfo {

    NLS defaultNLS;
    Map<Locale, NLS> localeNLS;

    public AbstractModuleInfo() {
        localeNLS = new HashMap<Locale, NLS>();
    }

    public String getPackageName() {
        return getClass().getPackage().getName();
    }

    public NLS getNLS() {
        if (defaultNLS == null)
            defaultNLS = createNLS( Locale.getDefault() );
        return defaultNLS;
    }

    public NLS getNLS( Locale locale ) {
        if (locale == null)
            throw new NullPointerException( "locale" );
        if (locale.equals( Locale.getDefault() ))
            return getNLS();

        NLS nls = localeNLS.get( locale );
        if (nls == null) {
            nls = createNLS( locale );
            localeNLS.put( locale, nls );
        }
        return nls;
    }

    protected NLS createNLS( Locale locale ) {
        String defaultBaseName = getPackageName() + ".NLS";
        NLS nls = new NLS( defaultBaseName, locale );
        return nls;
    }

}
