package net.bodz.bas.javadoc;

import java.util.Locale;

import net.bodz.bas.context.clg.SystemCLG;
import net.bodz.bas.i18n.ContextLocale;

public class JavadocLocale {

    public static Locale getLocale() {
        ContextLocale locale = SystemCLG.locale;
        return (Locale) locale.get();
    }

}
