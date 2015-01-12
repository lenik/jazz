package net.bodz.bas.i18n;

import java.util.Locale;

public class LocaleScr
        extends I18nScopedRef<Locale> {

    public LocaleScr() {
        super(Locale.class);
    }

    @Override
    public Locale getDefaultValue() {
        return Locale.getDefault();
    }

    public String getPath() {
        Locale locale = get();
        String tag = locale.toLanguageTag();
        return tag;
    }

}
