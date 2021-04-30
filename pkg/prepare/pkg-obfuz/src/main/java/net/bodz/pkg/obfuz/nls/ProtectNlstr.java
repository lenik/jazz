package net.bodz.pkg.obfuz.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNlstr;
import net.bodz.bas.i18n.nls.INlsTranslator;

public class ProtectNlstr
        extends ClassResourceNlstr {

    public ProtectNlstr(INlsTranslator next, Locale locale) {
        super(next, locale);
    }

    public ProtectNlstr(INlsTranslator next) {
        super(next);
    }

    public static final ProtectNlstr INSTANCE = new ProtectNlstr(null);

}
