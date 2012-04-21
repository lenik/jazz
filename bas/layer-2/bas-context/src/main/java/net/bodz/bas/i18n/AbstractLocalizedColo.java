package net.bodz.bas.i18n;

import java.util.Locale;

import net.bodz.bas.context.ContextLocal;

public class AbstractLocalizedColo<T>
        extends ContextLocal<T> {

    protected Locale getLocale() {
        Locale locale = LocaleColos.locale.get();
        return locale;
    }

}
