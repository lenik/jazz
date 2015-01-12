package net.bodz.bas.i18n;

import java.util.Locale;

import net.bodz.bas.ctx.scope.ScopedRef;

public abstract class I18nScopedRef<T>
        extends ScopedRef<T>
        implements II18nScrConsts {

    public I18nScopedRef(Class<T> valueType) {
        super(valueType);
    }

    protected Locale getLocale() {
        Locale locale = LOCALE.get();
        return locale;
    }

}
