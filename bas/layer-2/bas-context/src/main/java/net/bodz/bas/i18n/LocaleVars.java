package net.bodz.bas.i18n;

import java.util.Locale;

public class LocaleVars
        extends I18nScopedRef<Locale> {

    public LocaleVars() {
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
