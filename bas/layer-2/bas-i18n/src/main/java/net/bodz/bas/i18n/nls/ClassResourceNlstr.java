package net.bodz.bas.i18n.nls;

import java.util.Locale;

public class ClassResourceNlstr
        extends ResourceBundleNlstr {

    public ClassResourceNlstr(INlsTranslator next, Locale locale) {
        super(next, locale);
    }

    public ClassResourceNlstr(INlsTranslator next) {
        super(next);
    }

}
