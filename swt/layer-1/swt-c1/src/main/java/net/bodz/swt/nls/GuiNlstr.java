package net.bodz.swt.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNlstr;
import net.bodz.bas.i18n.nls.INlsTranslator;

public class GuiNlstr
        extends ClassResourceNlstr {

    public GuiNlstr(INlsTranslator next, Locale locale) {
        super(next, locale);
    }

    public GuiNlstr(INlsTranslator next) {
        super(next);
    }

    public static final GuiNlstr INSTANCE = new GuiNlstr(null);

}
