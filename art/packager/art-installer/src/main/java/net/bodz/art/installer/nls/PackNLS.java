package net.bodz.art.installer.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNLS;
import net.bodz.bas.i18n.nls.NLS;

public class PackNLS
        extends ClassResourceNLS {

    public PackNLS(NLS next, Locale locale) {
        super(next, locale);
    }

    public PackNLS(NLS next) {
        super(next);
    }

    public static final PackNLS PackNLS = new PackNLS(null);

}
