package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatScr
        extends I18nScopedRef<NumberFormat> {

    public NumberFormatScr() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getInstance(locale);
    }

}