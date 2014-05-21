package net.bodz.bas.i18n;

import java.util.Locale;

public class LocaleCtl
        extends I18nContextLocal<Locale> {

    public LocaleCtl() {
        super(Locale.class);
    }

    @Override
    public Locale getDefaultValue() {
        return Locale.getDefault();
    }

    public String getPath() {
        Locale locale = get();
        String tag = locale.toLanguageTag();
        // tag = locale.toString();
        return tag;
    }

}
