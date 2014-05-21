package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class IntegerFormatCtl
        extends I18nContextLocal<NumberFormat> {

    public IntegerFormatCtl() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getIntegerInstance(locale);
    }

}
