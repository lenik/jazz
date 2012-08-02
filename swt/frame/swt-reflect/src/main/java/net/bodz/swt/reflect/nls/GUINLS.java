package net.bodz.swt.reflect.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNLS;
import net.bodz.bas.i18n.nls.NLS;

public class GUINLS
        extends ClassResourceNLS {

    public GUINLS(NLS next, Locale locale) {
        super(next, locale);
    }

    public GUINLS(NLS next) {
        super(next);
    }

    public static final GUINLS GUINLS = new GUINLS(null);

}
