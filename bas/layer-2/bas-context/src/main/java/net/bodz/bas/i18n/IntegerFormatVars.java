package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class IntegerFormatVars
        extends I18nScopedRef<NumberFormat> {

    public IntegerFormatVars() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getIntegerInstance(locale);
    }

}
