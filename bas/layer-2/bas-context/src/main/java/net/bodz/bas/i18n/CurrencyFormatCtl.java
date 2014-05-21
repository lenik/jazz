package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatCtl
        extends I18nContextLocal<NumberFormat> {

    public CurrencyFormatCtl() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getCurrencyInstance(locale);
    }

}
