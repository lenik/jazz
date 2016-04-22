package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class PercentFormatVars
        extends I18nScopedRef<NumberFormat> {

    public PercentFormatVars() {
        super(NumberFormat.class);
    }

    @Override
    public NumberFormat getDefaultValue() {
        Locale locale = getLocale();
        return NumberFormat.getPercentInstance(locale);
    }

}
