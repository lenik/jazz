package net.bodz.swt.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNlstr;
import net.bodz.bas.i18n.nls.INlsTranslator;

public class ControlsNlstr
        extends ClassResourceNlstr {

    public ControlsNlstr(INlsTranslator next, Locale locale) {
        super(next, locale);
    }

    public ControlsNlstr(INlsTranslator next) {
        super(next);
    }

    public static final ControlsNlstr INSTANCE = new ControlsNlstr(null);

}
