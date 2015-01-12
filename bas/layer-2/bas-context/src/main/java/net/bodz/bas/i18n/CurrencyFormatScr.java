package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatScr
        extends I18nScopedRef<NumberFormat> {

    public CurrencyFormatScr() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getCurrencyInstance(locale);
    }

}
