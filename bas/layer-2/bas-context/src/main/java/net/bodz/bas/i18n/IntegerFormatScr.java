package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class IntegerFormatScr
        extends I18nScopedRef<NumberFormat> {

    public IntegerFormatScr() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getIntegerInstance(locale);
    }

}
