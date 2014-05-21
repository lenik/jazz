package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class PercentFormatCtl
        extends I18nContextLocal<NumberFormat> {

    public PercentFormatCtl() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getPercentInstance(locale);
    }

}
