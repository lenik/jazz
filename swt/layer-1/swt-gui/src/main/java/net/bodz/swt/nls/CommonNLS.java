package net.bodz.swt.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNLS;
import net.bodz.bas.i18n.nls.NLS;

public class CommonNLS
        extends ClassResourceNLS {

    public CommonNLS(NLS next, Locale locale) {
        super(next, locale);
    }

    public CommonNLS(NLS next) {
        super(next);
    }

    public static final CommonNLS CommonNLS = new CommonNLS(null);

}
