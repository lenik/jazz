package net.bodz.bas.i18n;

import java.util.Locale;

import net.bodz.bas.context.ContextLocal;

public abstract class I18nContextLocal<T>
        extends ContextLocal<T>
        implements II18nCtlConsts {

    public I18nContextLocal(Class<T> valueType) {
        super(valueType);
    }

    protected Locale getLocale() {
        Locale locale = LOCALE.get();
        return locale;
    }

}
