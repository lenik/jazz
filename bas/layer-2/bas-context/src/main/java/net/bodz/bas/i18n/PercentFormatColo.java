package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class PercentFormatColo
        extends AbstractLocalizedColo<NumberFormat> {

    @Override
    public NumberFormat getRoot() {
        Locale locale = getLocale();
        return NumberFormat.getPercentInstance(locale);
    }

}
