package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class PercentFormatScr
        extends I18nScopedRef<NumberFormat> {

    public PercentFormatScr() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getPercentInstance(locale);
    }

}
