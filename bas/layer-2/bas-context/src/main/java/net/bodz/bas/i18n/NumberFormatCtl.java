package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatCtl
        extends I18nContextLocal<NumberFormat> {

    public NumberFormatCtl() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getInstance(locale);
    }

}
