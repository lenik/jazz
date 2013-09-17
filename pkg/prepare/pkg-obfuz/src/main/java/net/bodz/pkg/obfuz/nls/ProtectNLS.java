package net.bodz.pkg.obfuz.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNLS;
import net.bodz.bas.i18n.nls.NLS;

public class ProtectNLS
        extends ClassResourceNLS {

    public ProtectNLS(NLS next, Locale locale) {
        super(next, locale);
    }

    public ProtectNLS(NLS next) {
        super(next);
    }

    public static final ProtectNLS ProtectNLS = new ProtectNLS(null);

}
