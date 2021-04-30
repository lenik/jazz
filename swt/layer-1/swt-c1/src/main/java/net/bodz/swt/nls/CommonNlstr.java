package net.bodz.swt.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNlstr;
import net.bodz.bas.i18n.nls.INlsTranslator;

public class CommonNlstr
        extends ClassResourceNlstr {

    public CommonNlstr(INlsTranslator next, Locale locale) {
        super(next, locale);
    }

    public CommonNlstr(INlsTranslator next) {
        super(next);
    }

    public static final CommonNlstr INSTANCE = new CommonNlstr(null);

}
